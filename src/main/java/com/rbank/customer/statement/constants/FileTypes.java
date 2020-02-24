package com.rbank.customer.statement.constants;

/**
 * @author Michael Philomin Raj
 *
 */

public final class FileTypes {
	
	private FileTypes() {

	}
	
	public static final String XML_CONTENT_TYPE_1 = "text/xml"; // For Swagger UI 
	public static final String XML_CONTENT_TYPE_2 = "application/xml"; // For Integration Test
	public static final String CSV_CONTENT_TYPE_1 = "application/vnd.ms-excel";  // For Swagger UI
	public static final String CSV_CONTENT_TYPE_2 = "text/csv"; // For Integration Test

	public static final String XML = "xml";
	public static final String CSV = "csv";
}
