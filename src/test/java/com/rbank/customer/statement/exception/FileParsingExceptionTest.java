/**
 * 
 */
package com.rbank.customer.statement.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Michael Philomin Raj
 *
 */
@RunWith(SpringRunner.class)
public class FileParsingExceptionTest {

	@Test
	public void TestFileParsingException(){		
		FileParsingException fileParsingException = new FileParsingException(234, "Parsing file - records.csv failed due to invalid data");
		Assert.assertNotEquals(null, fileParsingException);
		assertEquals(234, fileParsingException.getStatusCode());
		assertEquals("Parsing file - records.csv failed due to invalid data", fileParsingException.getMessage());
	}
}
