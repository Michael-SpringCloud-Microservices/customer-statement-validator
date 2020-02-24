/**
 * 
 */
package com.rbank.customer.statement.response;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbank.customer.statement.model.RecordDetail;

/**
 * @author Michael Philomin Raj
 *
 */

@RunWith(SpringRunner.class)
public class ValidationResultTest {

	
	@Test
	public void testValidationResult(){

		List<RecordDetail> reports = new ArrayList<RecordDetail>();
		RecordDetail report1 = new RecordDetail();
		report1.setTransactionReference("112806");
		report1.setDescription("Clothes from Peter de Vries");
		reports.add(report1);
		RecordDetail report2 = new RecordDetail();
		report2.setTransactionReference("112806");
		report2.setDescription("Tickets for Erik Dekker");
		reports.add(report2);
		
		Date sysDate = new Date();
		ValidationResult validationResult = new ValidationResult(sysDate,"some message",reports);			
		Assert.assertNotEquals(null, validationResult);
		Assert.assertNotEquals(null, validationResult.toString());
		assertEquals(sysDate, validationResult.getTimestamp());
		assertEquals("some message", validationResult.getMessage());
		
		List<RecordDetail> result = validationResult.getRecordDetails();
		assertEquals(2, result.size());
		assertEquals("112806", result.get(0).getTransactionReference());
		assertEquals("Clothes from Peter de Vries", result.get(0).getDescription());
		assertEquals("112806", result.get(1).getTransactionReference());
		assertEquals("Tickets for Erik Dekker", result.get(1).getDescription()); 
	}
}
