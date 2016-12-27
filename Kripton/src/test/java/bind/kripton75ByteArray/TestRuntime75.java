package bind.kripton75ByteArray;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime75 extends AbstractBaseTest {


	@Test
	public void testRun() throws Exception
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
