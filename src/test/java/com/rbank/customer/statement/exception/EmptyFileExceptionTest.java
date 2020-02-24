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
public class EmptyFileExceptionTest {

	@Test
	public void TestEmptyFileException(){
		EmptyFileException emptyFileException = new EmptyFileException(233,"File can't be empty");
		Assert.assertNotEquals(null, emptyFileException);
		assertEquals(233, emptyFileException.getStatusCode());
		assertEquals("File can't be empty", emptyFileException.getMessage());
	}
}