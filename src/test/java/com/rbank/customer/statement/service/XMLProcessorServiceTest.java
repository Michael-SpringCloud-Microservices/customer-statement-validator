package com.rbank.customer.statement.service;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
public class XMLProcessorServiceTest {
	
	@InjectMocks
	XMLProcessorService xmlProcessorService;

	@Test
	public void testConvertXmlToReportDetails() throws IOException{
		File xmlFile = new File(this.getClass().getResource("/xml/records.xml").getFile());
		InputStream is = new FileInputStream(xmlFile);
		MockMultipartFile multipartFile = new MockMultipartFile("xml", "records.xml", "text/xml", is);
		is.close();		
		List<RecordDetail> result = xmlProcessorService.processXml(multipartFile);
		Assert.assertNotEquals(null, result);
		assertEquals(10, result.size());
	}
}
