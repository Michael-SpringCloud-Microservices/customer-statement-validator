/**
 * 
 */
package com.rbank.customer.statement.response;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbank.customer.statement.response.Result;

/**
 * @author Michael Philomin Raj
 *
 */
@RunWith(SpringRunner.class)
public class BaseResultTest {

	
	@Test
	public void testBaseResult(){		
		Date sysDate = new Date();
		Result result = new Result(sysDate,"File can't be empty");			
		Assert.assertNotEquals(null, result);
		Assert.assertNotEquals(null, result.toString());
		assertEquals(sysDate, result.getTimestamp());
		assertEquals("File can't be empty", result.getMessage());
	}
}
