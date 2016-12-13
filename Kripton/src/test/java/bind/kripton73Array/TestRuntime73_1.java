package bind.kripton73Array;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime73_1 extends AbstractBaseTest {

	/**
	 * Test empty arrays
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testEmptyArraysRun() throws IOException, InstantiationException, IllegalAccessException
	{
		Assert.assertNotNull(new Bean73BindMap());
		
		Bean73 bean=new Bean73();
			
		bean.valueBeanArray=new Bean73[0];
		
		bean.valueBigDecimalArray=new BigDecimal[0];
		
		bean.valueBigIntegerArray=new BigInteger[0];
		
		bean.valueByteArray=new Byte[0];
		
		bean.valueCharacterArray=new Character[0];
		
		bean.valueDoubleArray=new Double[0];		
		
		bean.valueEnumArray=new Enum73[0];
				
		bean.valueFloatArray=new Float[0];
		
		bean.setValueIntArray(new Integer[0]);
		
		bean.valueLongArray=new Long[0];
		
		bean.valueShortArray=new Short[0];
		
		bean.valueStringArray=new String[0];
		
//		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
}
