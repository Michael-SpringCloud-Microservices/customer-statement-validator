package com.rbank.customer.statement;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rbank.customer.statement.config.RestTemplateAutoConfiguration;
import com.rbank.customer.statement.model.RecordDetail;
import com.rbank.customer.statement.response.Result;
import com.rbank.customer.statement.response.ValidationResult;


/**
 * @author Michael Philomin Raj
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Import({RestTemplateAutoConfiguration.class })
public class CustomerStatementValidatorApplicationTests {

	@Autowired
	RestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Test
	public void contextLoads() {
		assertNotNull(restTemplate);
	}

	@Test
	public void testUploadedCsvRecords() throws URISyntaxException,IOException
	{
		final String baseUrl = "http://localhost:" + randomServerPort + "/statement/process";
		URI uri = new URI(baseUrl);
		File csvFile = new File(this.getClass().getResource("/csv/records.csv").getFile());
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("file", new FileSystemResource(csvFile));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

		ResponseEntity<ValidationResult> result = restTemplate.postForEntity(uri, requestEntity, ValidationResult.class);
		Assert.assertNotEquals(null, result);
		assertNotNull(result.getStatusCodeValue());
		Assert.assertEquals(231, result.getStatusCodeValue());
		assertNotNull(result.getBody());
		ValidationResult validationResult = result.getBody();
		assertNotNull(validationResult.getMessage());
		List<RecordDetail> failedRecords = validationResult.getRecordDetails();
		assertNotNull(failedRecords);
		Assert.assertEquals(2, failedRecords.size());

		RecordDetail report1 = failedRecords.get(0);
		assertNotNull(report1);
		List<String> failureReasons = report1.getFailureReasons();
		assertNotNull(failureReasons);
		Assert.assertEquals(1, failureReasons.size());
		Assert.assertEquals("Record is duplicated", failureReasons.get(0));

		assertNotNull(report1);
		Assert.assertEquals("112806", report1.getTransactionReference());
		Assert.assertEquals("Clothes from Peter de Vries", report1.getDescription());

		RecordDetail report2 = failedRecords.get(1);
		assertNotNull(report2);
		assertNotNull(report2);
		Assert.assertEquals("112806", report2.getTransactionReference());
		Assert.assertEquals("Tickets for Erik Dekker", report2.getDescription());
		List<String> failureReasons1 = report2.getFailureReasons();
		assertNotNull(failureReasons1);
		Assert.assertEquals(1, failureReasons1.size());
		Assert.assertEquals("Record is duplicated", failureReasons1.get(0));
	}

	@Test
	public void testUploadedCSVWithAllValidRecords() throws URISyntaxException,IOException
	{
		final String baseUrl = "http://localhost:" + randomServerPort + "/statement/process";
		URI uri = new URI(baseUrl);
		File csvFile = new File(this.getClass().getResource("/csv/records-all-valid.csv").getFile());
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("file", new FileSystemResource(csvFile));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

		ResponseEntity<Result> result = restTemplate.postForEntity(uri, requestEntity, Result.class);
		Assert.assertNotEquals(null, result);
		assertNotNull(result.getStatusCodeValue());
		Assert.assertEquals(230, result.getStatusCodeValue());
		Result baseResult = result.getBody();
		assertNotNull(baseResult);
		assertNotNull(baseResult.getMessage());
		Assert.assertEquals("All the records in the given file " + "records-all-valid.csv" + " are valid", baseResult.getMessage());		
	}

	@Test
	public void testUploadedCSVWithoutRecords() throws URISyntaxException,IOException
	{
		final String baseUrl = "http://localhost:" + randomServerPort + "/statement/process";
		URI uri = new URI(baseUrl);
		File csvFile = new File(this.getClass().getResource("/csv/records-empty.csv").getFile());
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("file", new FileSystemResource(csvFile));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

		ResponseEntity<Result> result = restTemplate.postForEntity(uri, requestEntity, Result.class);
		Assert.assertNotEquals(null, result);
		assertNotNull(result.getStatusCodeValue());
		Assert.assertEquals(233, result.getStatusCodeValue());
		Result baseResult = result.getBody();
		assertNotNull(baseResult);
		assertNotNull(baseResult.getMessage());
		Assert.assertEquals("File can't be empty", baseResult.getMessage());		
	}


	
	@Test
	public void testUploadedXmlRecords() throws URISyntaxException,IOException
	{
		final String baseUrl = "http://localhost:" + randomServerPort + "/statement/process";
		URI uri = new URI(baseUrl);
		File xmlFile = new File(this.getClass().getResource("/xml/records.xml").getFile());
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("file", new FileSystemResource(xmlFile));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

		ResponseEntity<ValidationResult> result = restTemplate.postForEntity(uri, requestEntity, ValidationResult.class);

		Assert.assertNotEquals(null, result);
		assertNotNull(result.getStatusCodeValue());
		Assert.assertEquals(231, result.getStatusCodeValue());
		ValidationResult validationResult = result.getBody();
		assertNotNull(validationResult);
		List<RecordDetail> failedRecords = validationResult.getRecordDetails();
		assertNotNull(failedRecords);
		Assert.assertEquals(2, failedRecords.size());

		RecordDetail report1 = failedRecords.get(0);
		assertNotNull(report1);
		Assert.assertEquals("154270", report1.getTransactionReference());
		Assert.assertEquals("Candy for Peter de Vries", report1.getDescription());
		List<String> failureReasons = report1.getFailureReasons();
		assertNotNull(failureReasons);
		Assert.assertEquals(1, failureReasons.size());
		Assert.assertEquals("End Balance is mismatched", failureReasons.get(0));


		RecordDetail report2 = failedRecords.get(1);
		assertNotNull(report2);
		Assert.assertEquals("140269", report2.getTransactionReference());
		Assert.assertEquals("Tickets for Vincent Dekker", report2.getDescription());
		List<String> failureReasons1 = report1.getFailureReasons();
		assertNotNull(failureReasons1);
		Assert.assertEquals(1, failureReasons1.size());
		Assert.assertEquals("End Balance is mismatched", failureReasons1.get(0));
	}


	@Test
	public void testUploadedXMLWithAllValidRecords() throws URISyntaxException,IOException
	{
		final String baseUrl = "http://localhost:" + randomServerPort + "/statement/process";
		URI uri = new URI(baseUrl);
		File xmlFile = new File(this.getClass().getResource("/xml/records-all-valid.xml").getFile());
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("file", new FileSystemResource(xmlFile));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

		ResponseEntity<Result> result = restTemplate.postForEntity(uri, requestEntity, Result.class);
		Assert.assertNotEquals(null, result);
		assertNotNull(result.getStatusCodeValue());
		Assert.assertEquals(230, result.getStatusCodeValue());
		Result baseResult = result.getBody();
		assertNotNull(baseResult);
		assertNotNull(baseResult.getMessage());
		Assert.assertEquals("All the records in the given file " + "records-all-valid.xml" + " are valid", baseResult.getMessage());		
	}


	@Test
	public void testUploadedXmlWithoutRecords() throws URISyntaxException,IOException
	{
		final String baseUrl = "http://localhost:" + randomServerPort + "/statement/process";
		URI uri = new URI(baseUrl);
		File xmlFile = new File(this.getClass().getResource("/xml/records-empty.xml").getFile());
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("file", new FileSystemResource(xmlFile));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

		ResponseEntity<Result> result = restTemplate.postForEntity(uri, requestEntity, Result.class);
		Assert.assertNotEquals(null, result);
		assertNotNull(result.getStatusCodeValue());
		Assert.assertEquals(233, result.getStatusCodeValue());
		Result baseResult = result.getBody();
		assertNotNull(baseResult);
		assertNotNull(baseResult.getMessage());
		Assert.assertEquals("File can't be empty", baseResult.getMessage());		
	}

	
	@Test
	public void testUploadedTxtFile() throws URISyntaxException,IOException
	{
		final String baseUrl = "http://localhost:" + randomServerPort + "/statement/process";
		URI uri = new URI(baseUrl);
		File textFile = new File(this.getClass().getResource("/text/records.txt").getFile());
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("file", new FileSystemResource(textFile));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

		ResponseEntity<Result> result = restTemplate.postForEntity(uri, requestEntity, Result.class);
		Assert.assertNotEquals(null, result);
		assertNotNull(result.getStatusCodeValue());
		Assert.assertEquals(232, result.getStatusCodeValue());
		Result baseResult = result.getBody();
		assertNotNull(baseResult);
		assertNotNull(baseResult.getMessage());
		Assert.assertEquals("The given file format text/plain is not supported", baseResult.getMessage());		
	}



}
