package com.rbank.customer.statement.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.rbank.customer.statement.constants.CustomHttpStatusCodes;
import com.rbank.customer.statement.constants.ErrorMessages;
import com.rbank.customer.statement.constants.FileTypes;
import com.rbank.customer.statement.exception.EmptyFileException;
import com.rbank.customer.statement.exception.FileParsingException;
import com.rbank.customer.statement.exception.FileSizeNotAllowedException;

/**
 * @author Michael Philomin Raj
 *
 */

public class ValidationUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtil.class);

	private ValidationUtil() {

	}

	/**
	 * 
	 * @param fileType
	 * @return the file extension
	 */
	public static String getFileExtension(String fileType){
		String fileExtension = fileType;
		if (fileType.equalsIgnoreCase(FileTypes.XML_CONTENT_TYPE_1) || fileType.equalsIgnoreCase(FileTypes.XML_CONTENT_TYPE_2)){
			fileExtension = FileTypes.XML;
		}else if (fileType.equalsIgnoreCase(FileTypes.CSV_CONTENT_TYPE_1) || fileType.equalsIgnoreCase(FileTypes.CSV_CONTENT_TYPE_2)){
			fileExtension = FileTypes.CSV;
		}
		return fileExtension;
	}

	/**
	 * This method will validate the file type and size
	 * @param file
	 * @param fileName
	 * @param fileType
	 * @throw EmptyFileException
	 * @throw FileSizeNotAllowedException
	 */
	public static void validateFileSize(MultipartFile file, String fileName, String fileType) {
		// Check for empty file
		if (fileType.equalsIgnoreCase(FileTypes.CSV_CONTENT_TYPE_1) || fileType.equalsIgnoreCase(FileTypes.CSV_CONTENT_TYPE_2)){
			isCsvFileEmpty(file, fileName);
		}else if(file.isEmpty()){
			throw new EmptyFileException(CustomHttpStatusCodes.HTTP_STATUS_233,ErrorMessages.FILE_CANT_BE_EMPTY);
		}

		// Check for file's allowed max size
		if(file.getSize()>8000000){
			throw new FileSizeNotAllowedException(CustomHttpStatusCodes.HTTP_STATUS_235,ErrorMessages.FILE_SIZE_NOT_ALLOWED);
		}

	}

	private static void isCsvFileEmpty(MultipartFile file,String fileName){
		try (InputStream is = file.getInputStream();) {
			CSVParser csvParser = CSVParser.parse(is,StandardCharsets.US_ASCII, 
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			if(csvParser.getRecords().isEmpty()){
				throw new EmptyFileException(CustomHttpStatusCodes.HTTP_STATUS_233,ErrorMessages.FILE_CANT_BE_EMPTY);
			}
		}catch(EmptyFileException efe){
			throw efe;
		}catch(Exception e){
			LOGGER.error("Parsing file with fileName = {} failed. And the reason is : {}", fileName , e);
			throw new FileParsingException(CustomHttpStatusCodes.HTTP_STATUS_234,"Parsing file - " + fileName + " failed due to invalid data");
		}
	}

}
