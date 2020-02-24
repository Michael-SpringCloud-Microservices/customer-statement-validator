package com.rbank.customer.statement.response;

import java.util.Date;
import java.util.List;

import com.rbank.customer.statement.model.RecordDetail;

/**
 * @author Michael Philomin Raj
 *
 */

public class ValidationResult extends Result {


	private List<RecordDetail> recordDetails;

	public ValidationResult(){

	}

	/**
	 * @param timestamp
	 * @param message
	 * @param reports
	 */
	public ValidationResult(Date timestamp, String message, List<RecordDetail> recordDetails) {
		super(timestamp,message);
		this.recordDetails = recordDetails;
	}

	/**
	 * @return the recordDetails
	 */
	public List<RecordDetail> getRecordDetails() {
		return recordDetails;
	}

	/**
	 * @param recordDetails the recordDetails to set
	 */
	public void setRecordDetails(List<RecordDetail> recordDetails) {
		this.recordDetails = recordDetails;
	}

}
