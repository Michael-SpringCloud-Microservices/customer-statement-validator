package com.rbank.customer.statement.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbank.customer.statement.model.RecordDetail;


/**
 * @author Michael Philomin Raj
 *
 */

@RunWith(SpringRunner.class)
public class CSVProcessorServiceTest {

	@InjectMocks
	CSVProcessorService csvProcessorService;
	
	@Test
	public void testConvertCsvToRecordDetails()throws IOException{
		File csvFile = new File(this.getClass().getResource("/csv/records.csv").getFile());
		InputStream is = new FileInputStream(csvFile);
		MockMultipartFile multipartFile = new MockMultipartFile("csv", "records.csv", "text/csv", is);
		is.close();
		List<RecordDetail> result = csvProcessorService.processCsv(multipartFile);
		Assert.assertNotEquals(null, result);
		assertEquals(10, (result.stream().filter(r->r.isValid()).collect(Collectors.toList())).size());
	}
	

	

}
