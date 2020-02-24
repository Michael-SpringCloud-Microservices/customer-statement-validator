package com.rbank.customer.statement.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rbank.customer.statement.constants.ErrorMessages;
import com.rbank.customer.statement.model.RecordDetail;

/**
 * @author Michael Philomin Raj
 *
 */

@Service
public class ValidationService {

	/**
	 * 
	 * @param recordDetails
	 * @return List of RecordDetail failed in business rules validation
	 */
	public List<RecordDetail> validateRecords(List<RecordDetail> recordDetails){
		List<RecordDetail> reports = new ArrayList<>();
		//Identifying failed records ( ie. records having the duplication of Transaction reference or the improper End Balance)
		List<String> processedRecords = new ArrayList<>();

		recordDetails.forEach(recordDetail ->{
			if(!recordDetail.isValid()){ 
				reports.add(recordDetail); // records which failed in basic validation due to invalid data 
			}else{ 
				if(validateBusinessRuels(processedRecords,recordDetail)){
					reports.add(recordDetail); // records which failed with given business rules validation like duplication or improper end-balance 
				}
			} 
			processedRecords.add(recordDetail.getTransactionReference()); 
		});
		processedRecords.clear();
		return reports; 
	}

	private boolean validateBusinessRuels(List<String> processedRecords,RecordDetail recordDetail){
		boolean isDuplicated  = validateTransactionReference(processedRecords,recordDetail);
		boolean isInvalidEndBalance = false;
		if(!isDuplicated)
			isInvalidEndBalance = validateEndBalance(recordDetail);

		return isDuplicated || isInvalidEndBalance;
	}

	private boolean validateTransactionReference(List<String> processedRecords,RecordDetail recordDetail){
		boolean res = false;
		if(!recordDetail.getTransactionReference().isEmpty() && !processedRecords.isEmpty() && processedRecords.contains(recordDetail.getTransactionReference())){
			recordDetail.setValid(false);
			recordDetail.getFailureReasons().add(ErrorMessages.RECORD_DUPLICATED);
			res = true;
		}
		return res;
	}

	private boolean validateEndBalance(RecordDetail recordDetail){
		boolean res = false;		
		BigDecimal startBalance = new BigDecimal(recordDetail.getStartBalance()).setScale(2);
		BigDecimal mutation = new BigDecimal(recordDetail.getMutation()).setScale(2); 
		BigDecimal endBalance = new BigDecimal(recordDetail.getEndBalance()).setScale(2); 		
		if((startBalance.add(mutation)).compareTo(endBalance)!=0){
			recordDetail.setValid(false);
			recordDetail.getFailureReasons().add(ErrorMessages.BALANCE_MISMATCHED);
			res = true;
		}
		return res;
	}
}

