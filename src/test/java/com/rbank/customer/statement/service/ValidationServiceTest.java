package com.rbank.customer.statement.service;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbank.customer.statement.data.UserStatementDataProvider;
import com.rbank.customer.statement.model.RecordDetail;


/**
 * @author Michael Philomin Raj
 *
 */
@RunWith(SpringRunner.class)
public class ValidationServiceTest {

	@InjectMocks
	ValidationService validationService;	

	@Test
	public void CsvWithGivenData() throws Exception {

		// Stubbing of recordDetails
		List<RecordDetail> recordDetails = UserStatementDataProvider.getCsvData();

		List<RecordDetail> result = validationService.validateRecords(recordDetails);
		Assert.assertNotEquals(null, result);
		assertEquals(2, result.size());
		assertEquals("112806", result.get(0).getTransactionReference());
		assertEquals("Clothes from Peter de Vries", result.get(0).getDescription());
		assertEquals("112806", result.get(1).getTransactionReference());
		assertEquals("Tickets for Erik Dekker", result.get(1).getDescription());
	}

	@Test
	public void XmlWithGivenData() throws Exception {

		// Stubbing of recordDetails
		List<RecordDetail> recordDetails = UserStatementDataProvider.getXmLData();

		List<RecordDetail> result = validationService.validateRecords(recordDetails);
		Assert.assertNotEquals(null, result);
		assertEquals(2, result.size());
		assertEquals("154270", result.get(0).getTransactionReference());
		assertEquals("Candy for Peter de Vries", result.get(0).getDescription());
		assertEquals("140269", result.get(1).getTransactionReference());
		assertEquals("Tickets for Vincent Dekker", result.get(1).getDescription());		
	}
	

}
