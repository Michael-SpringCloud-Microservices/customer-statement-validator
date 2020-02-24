package com.rbank.customer.statement.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbank.customer.statement.constants.CustomHttpStatusCodes;
import com.rbank.customer.statement.constants.FieldNames;
import com.rbank.customer.statement.exception.FileParsingException;
import com.rbank.customer.statement.model.RecordDetail;

/**
 * @author Michael Philomin Raj
 *
 */

@Service
public class CSVProcessorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CSVProcessorService.class);

	/**
	 * 
	 * @param file
	 * @return List of RecordDetail
	 * @throws IOException
	 */
	public List<RecordDetail> processCsv(MultipartFile file) throws IOException{
		String fileName = file.getOriginalFilename(); 
		List<RecordDetail> recordDetails = null;
		try (InputStream is = file.getInputStream();) {
			CSVParser csvParser = CSVParser.parse(is,StandardCharsets.US_ASCII,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			recordDetails = csvParser.getRecords().stream().parallel().map(csvRecord -> {
				RecordDetail recordDetail = new RecordDetail();
				recordDetail.setTransactionReference(csvRecord.get(FieldNames.REFERENCE));
				recordDetail.setAccountNumber(csvRecord.get(FieldNames.ACCOUNT_NUMBER));
				recordDetail.setDescription(csvRecord.get(FieldNames.DESCRIPTION));
				recordDetail.setStartBalance(csvRecord.get(FieldNames.START_BALANCE));
				recordDetail.setMutation(csvRecord.get(FieldNames.MUTATION));
				recordDetail.setEndBalance(csvRecord.get(FieldNames.END_BALANCE));	
				return recordDetail;				
			}).collect(Collectors.toList());
		}catch(Exception e){
			LOGGER.error("Parsing file with fileName = {} failed. And the reason is : {}", fileName , e);
			throw new FileParsingException(CustomHttpStatusCodes.HTTP_STATUS_234,String.join(" ", "Parsing file -",fileName,"failed due to invalid data"));
		}
		return recordDetails;
	}
}
