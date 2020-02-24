package com.rbank.customer.statement.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rbank.customer.statement.constants.CustomHttpStatusCodes;
import com.rbank.customer.statement.exception.InvalidDataException;
import com.rbank.customer.statement.model.RecordDetail;
import com.rbank.customer.statement.response.Result;
import com.rbank.customer.statement.response.ValidationResult;
import com.rbank.customer.statement.service.StatementService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Michael Philomin Raj
 *
 */

@RestController
@RequestMapping("/statement")
public class StatementController {

	@Autowired
	private StatementService statementService;


	/**
	 * This method will process the uploaded file 
	 * @param file
	 * @return ResponseEntity of Result which contains the record(s) failed in validation
	 * @throws IOException
	 */
	@PostMapping(value = "/process")  
	@ApiOperation(value = "Make a POST request to upload the file - Only xml or csv formats are allowed",produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiResponses({
		@ApiResponse(code = 230, message = "The file is valid", response = Result.class),
		@ApiResponse(code = 231, message = "The file has invalid record(s)", response = ValidationResult.class),
		@ApiResponse(code = 232, message = "The uploaded file format is not supported", response = Result.class),
		@ApiResponse(code = 233, message = "The uploaded file is empty", response = Result.class),
		@ApiResponse(code = 234, message = "The uploaded file could not be parsed", response = Result.class),
	})
	public ResponseEntity<Result> processFile(
			@ApiParam(name = "file", value = "Select the file to Upload", required = true)
			@RequestParam("file") MultipartFile file) throws IOException  {
		String fileName = file.getOriginalFilename();
		List<RecordDetail> recordDetails = statementService.processFile(file);
		return generateResult(fileName, recordDetails);
	}	

	private ResponseEntity<Result> generateResult(String fileName,List<RecordDetail> recordDetails) {
		generateFailure(fileName, recordDetails);
		return generateSuccess(fileName);
	}

	private ResponseEntity<Result> generateSuccess(String fileName) {
		Result result = new Result(new Date(),String.join(" ", "All the records in the given file",fileName,"are valid"));
		return ResponseEntity.status(CustomHttpStatusCodes.HTTP_STATUS_230).body(result);
	}

	private void generateFailure(String fileName, List<RecordDetail> recordDetails) {
		if(!recordDetails.isEmpty()){
			throw new InvalidDataException(CustomHttpStatusCodes.HTTP_STATUS_231,recordDetails, String.join(" ", "The given file",fileName,"has invalid records"));
		}
	}
}