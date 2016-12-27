package bind.kripton72;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime72A_3 extends AbstractBaseTest {

	/**
	 * Test all collection null
	 * @throws Exception 
	 */
	@Test
	public void testWithNullCollectionsRun() throws Exception
	{
		Assert.assertNotNull(new Bean72ABindMap());
		
		Bean72A bean=new Bean72A();
		
		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
	
}
