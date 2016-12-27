package bind.kripton72;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime72_3 extends AbstractBaseTest {

	/**
	 * Test all collection null
	 * @throws Exception 
	 */
	@Test
	public void testWithNullCollectionsRun() throws Exception
	{
		Assert.assertNotNull(new Bean72BindMap());
		
		Bean72 bean=new Bean72();
		
		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
	
}
