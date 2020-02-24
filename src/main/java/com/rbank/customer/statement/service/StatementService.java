package com.rbank.customer.statement.service;


import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbank.customer.statement.constants.CustomHttpStatusCodes;
import com.rbank.customer.statement.constants.FileTypes;
import com.rbank.customer.statement.exception.UnsupportedFileFormatException;
import com.rbank.customer.statement.model.RecordDetail;
import com.rbank.customer.statement.util.ValidationUtil;

/**
 * @author Michael Philomin Raj
 *
 */

@Service
public class StatementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatementService.class);

	@Autowired
	CSVProcessorService csvProcessorService;

	@Autowired
	XMLProcessorService xmlProcessorService;

	@Autowired
	private ValidationService validationService;


	/**
	 * This method will parse,process and validate the uploaded file content
	 * @param file
	 * @return List of RecordDetail
	 * @throws IOException
	 */
	public List<RecordDetail> processFile(MultipartFile file) throws IOException{
		String fileName = file.getOriginalFilename();
		String fileType = file.getContentType();
		LOGGER.info("FileValidationService -> fileName={} and fileType={}" , fileName, fileType);
		ValidationUtil.validateFileSize(file, fileName, fileType); 
		return processAndValidateRecords(file, fileType);	
	}


	private List<RecordDetail> processAndValidateRecords(MultipartFile file, String fileType) throws IOException{
		String fileExtension = ValidationUtil.getFileExtension(fileType); 
		List<RecordDetail> recordDetails;
		switch (fileExtension) {
		case FileTypes.XML:
			recordDetails = processXmlFile(file);
			return validateRecords(recordDetails);
		case FileTypes.CSV:
			recordDetails = processCsvFile(file);			
			return validateRecords(recordDetails);
		default:
			throw new UnsupportedFileFormatException(CustomHttpStatusCodes.HTTP_STATUS_232,String.join(" ", "The given file format",fileType,"is not supported"));
		}
	} 

	private List<RecordDetail> processXmlFile(MultipartFile file){
		return xmlProcessorService.processXml(file);
	}

	private List<RecordDetail> processCsvFile(MultipartFile file) throws IOException{
		return csvProcessorService.processCsv(file);
	}

	private List<RecordDetail> validateRecords(List<RecordDetail> recordDetails){
		return validationService.validateRecords(recordDetails);
	}
}
