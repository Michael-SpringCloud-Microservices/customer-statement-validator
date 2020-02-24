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
public class UnsupportedFileFormatExceptionTest {

	@Test
	public void TestUnsupportedFileFormatException(){		
		UnsupportedFileFormatException unsupportedFileFormatException = new UnsupportedFileFormatException(232, "Given file format is not supported");
		Assert.assertNotEquals(null, unsupportedFileFormatException);
		assertEquals(232, unsupportedFileFormatException.getStatusCode());
		assertEquals("Given file format is not supported", unsupportedFileFormatException.getMessage());
	}
}
