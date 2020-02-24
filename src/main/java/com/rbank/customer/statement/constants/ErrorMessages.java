package com.rbank.customer.statement.constants;


/**
 * @author Michael Philomin Raj
 *
 */

public final class ErrorMessages {

	private ErrorMessages() {

	}

	public static final String CANT_BE_BLANK_OR_NULL = "can not be blank or null";
	public static final String SHOULD_BE_ALPHA_NUMERIC = "(IBAN) shoul be alphanumeric";
	public static final String SHOULD_BE_NUMERIC = "shoul be numeric value";
	public static final String SHOULD_BE_VALID_DECIMAL = "shoul be valid decimal value";
	public static final String FILE_CANT_BE_EMPTY = "File can't be empty";
	public static final String FILE_SIZE_NOT_ALLOWED = "Uploaded file size is not allowed. The maximum size allowed is 8 MB"; 
	public static final String RECORD_DUPLICATED = "Record is duplicated";
	public static final String BALANCE_MISMATCHED = "End Balance is mismatched";

}
