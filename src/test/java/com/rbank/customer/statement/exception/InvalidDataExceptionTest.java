/**
 * 
 */
package com.rbank.customer.statement.exception;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbank.customer.statement.constants.ErrorMessages;
import com.rbank.customer.statement.model.RecordDetail;

/**
 * @author Michael Philomin Raj
 *
 */
@RunWith(SpringRunner.class)
public class InvalidDataExceptionTest {

	@Test
	public void TestInvalidDataException(){
		List<RecordDetail> recordDetails = new ArrayList<RecordDetail>();

		RecordDetail recordDetail = new RecordDetail();
		recordDetail.setTransactionReference("177666");
		recordDetail.setDescription("Flowers for Rik Theuß");
		recordDetail.setValid(false);
		List<String> failureReasons = new ArrayList<String>();
		failureReasons.add(ErrorMessages.BALANCE_MISMATCHED);
		recordDetail.setFailureReasons(failureReasons);
		recordDetails.add(recordDetail);

		InvalidDataException invalidDataException = new InvalidDataException(231,recordDetails,"The given file : records.csv has invalid records");
		Assert.assertNotEquals(null, invalidDataException);
		assertEquals(231, invalidDataException.getStatusCode());
		assertEquals("The given file : records.csv has invalid records", invalidDataException.getMessage());
		Assert.assertNotEquals(null, invalidDataException.getFailedRecords());
		assertEquals(1, invalidDataException.getFailedRecords().size());
		RecordDetail recordDetail2 = invalidDataException.getFailedRecords().get(0);
		assertEquals(ErrorMessages.BALANCE_MISMATCHED, recordDetail2.getFailureReasons().get(0));

	}
}
