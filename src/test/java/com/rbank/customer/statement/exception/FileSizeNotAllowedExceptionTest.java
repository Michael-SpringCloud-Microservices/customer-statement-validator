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
public class FileSizeNotAllowedExceptionTest {

	@Test
	public void TestFileSizeNotAllowedException(){		
		FileSizeNotAllowedException fileSizeNotAllowedException = new FileSizeNotAllowedException(235,"Uploaded file size is not allowed. The maximum size allowed is 8 MB");
		Assert.assertNotEquals(null, fileSizeNotAllowedException);
		assertEquals(235, fileSizeNotAllowedException.getStatusCode());
		assertEquals("Uploaded file size is not allowed. The maximum size allowed is 8 MB", fileSizeNotAllowedException.getMessage());
	}
}
