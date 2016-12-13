package bind.kripton72;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime72_3 extends AbstractBaseTest {

	/**
	 * Test all collection null
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testWithNullCollectionsRun() throws IOException, InstantiationException, IllegalAccessException
	{
		Assert.assertNotNull(new Bean72BindMap());
		
		Bean72 bean=new Bean72();
		
		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
	
}
