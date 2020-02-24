package com.rbank.customer.statement.handler;


import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rbank.customer.statement.exception.EmptyFileException;
import com.rbank.customer.statement.exception.FileParsingException;
import com.rbank.customer.statement.exception.FileSizeNotAllowedException;
import com.rbank.customer.statement.exception.InvalidDataException;
import com.rbank.customer.statement.exception.UnsupportedFileFormatException;
import com.rbank.customer.statement.response.Result;
import com.rbank.customer.statement.response.ValidationResult;

/**
 * @author Michael Philomin Raj
 *
 */

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(InvalidDataException.class)
	public final ResponseEntity<ValidationResult> handleInvalidDataException(InvalidDataException ex, WebRequest request){
		log.error("The given file validation is failed as it has invalid data : ", ex);
		ValidationResult result = new ValidationResult(new Date(), 
				ex.getMessage(), ex.getFailedRecords());		
		return ResponseEntity.status(ex.getStatusCode()).body(result);
	}

	@ExceptionHandler(UnsupportedFileFormatException.class)
	public final ResponseEntity<Result> handleUnsupportedFileFormatException(UnsupportedFileFormatException ex, WebRequest request){
		log.error("The given file validation is failed as the format or type is not supported : ", ex);
		Result result = new Result(new Date(), 
				ex.getMessage());
		return ResponseEntity.status(ex.getStatusCode()).body(result);		
	}

	@ExceptionHandler(EmptyFileException.class)
	public final ResponseEntity<Result> handleAllExceptions(EmptyFileException ex, WebRequest request){
		log.error("The given file validation is failed as the file is empty : ", ex);		
		Result result = new Result(new Date(), 
				ex.getMessage());
		return ResponseEntity.status(ex.getStatusCode()).body(result);
	}

	@ExceptionHandler(FileParsingException.class)
	public final ResponseEntity<Result> handleFileParsingException(FileParsingException ex, WebRequest request){
		log.error("The given file validation is failed as the given file is not parsable : ", ex);
		Result result = new Result(new Date(), 
				ex.getMessage());
		return ResponseEntity.status(ex.getStatusCode()).body(result);
	}

	@ExceptionHandler(IOException.class)
	public final ResponseEntity<Result> handleIOException(IOException ex, WebRequest request){
		log.error("The given file validation is failed due to IO exception : ", ex);
		Result result = new Result(new Date(), 
				ex.getMessage());
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(FileSizeNotAllowedException.class)
	public final ResponseEntity<Result> handleFileSizeNotAllowedException(FileSizeNotAllowedException ex, WebRequest request){
		log.error("The given file size is larger than the allowed one. Details : ", ex);
		Result result = new Result(new Date(), 
				ex.getMessage());
		return ResponseEntity.status(ex.getStatusCode()).body(result);
	}

}
