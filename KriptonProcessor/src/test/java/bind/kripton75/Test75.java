package bind.kripton75;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class Test75 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws Throwable
	{
		buildBindProcessorTest(Bean75.class);
	}
		
	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException
	{
		Assert.assertNotNull(new Bean75BindMap());
		
		Bean75 bean=new Bean75();
			
		bean.valueByteArray=new Byte[2];
		bean.valueByteArray[0]='1';
		bean.valueByteArray[1]='2';
				
		bean.valueByteTypeArray="hello world2!".getBytes();
		
		check(bean);
	}

	
}
