package com.rbank.customer.statement.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Michael Philomin Raj
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

	private Date timestamp;
	private String message;

	public Result(){

	}

	/**
	 * @param timestamp
	 * @param message
	 */
	public Result(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
