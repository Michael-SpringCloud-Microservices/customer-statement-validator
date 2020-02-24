package com.rbank.customer.statement.exception;


/**
 * @author Michael Philomin Raj
 *
 */

public class UnsupportedFileFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final int statusCode;
	
	public UnsupportedFileFormatException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}
	
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
}
