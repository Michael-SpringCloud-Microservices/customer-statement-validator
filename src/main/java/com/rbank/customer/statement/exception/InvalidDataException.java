package com.rbank.customer.statement.exception;

import java.util.List;

import com.rbank.customer.statement.model.RecordDetail;

/**
 * @author Michael Philomin Raj
 *
 */

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final  List<RecordDetail> failedRecords;
	private final  int statusCode;

	public InvalidDataException(int statusCode, List<RecordDetail> failedRecords, String message) {
		super(message);
		this.failedRecords = failedRecords;
		this.statusCode = statusCode;
	}

	/**
	 * @return the failedRecords
	 */
	public List<RecordDetail> getFailedRecords() {
		return failedRecords;
	}


	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
}
