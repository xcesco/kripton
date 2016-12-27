package bind.kripton72;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime72_2 extends AbstractBaseTest {

	/**
	 * Test all elements with null elements
	 * @throws Exception 
	 */
	@Test
	public void testWithNullElementsRun() throws Exception
	{
		Assert.assertNotNull(new Bean72BindMap());
		
		Bean72 bean=new Bean72();
			
		bean.valueBeanSet=new HashSet<Bean72>();
		bean.valueBeanSet.add(null);				
		bean.valueBeanSet.add(new Bean72("helloSet2"));
		bean.valueBeanSet.add(new Bean72("helloSet3"));
		
		bean.valueBigDecimalSet=new HashSet<BigDecimal>();
		bean.valueBigDecimalSet.add(null);
		bean.valueBigDecimalSet.add(BigDecimal.TEN);
		bean.valueBigDecimalSet.add(BigDecimal.ZERO);
		
		bean.valueBigIntegerSet=new HashSet<BigInteger>();
		bean.valueBigIntegerSet.add(null);
		bean.valueBigIntegerSet.add(BigInteger.TEN);
		bean.valueBigIntegerSet.add(BigInteger.ZERO);
		
		bean.valueByteSet=new HashSet<>();
		bean.valueByteSet.add(null);
		bean.valueByteSet.add((byte)2);
		bean.valueByteSet.add((byte)3);
		
		bean.valueCharacterSet=new HashSet<Character>();
		bean.valueCharacterSet.add(null);
		bean.valueCharacterSet.add('b');
		bean.valueCharacterSet.add('c');
		
		bean.valueDoubleSet=new HashSet<Double>();		
		bean.valueDoubleSet.add(null);
		bean.valueDoubleSet.add(24.0);
		
		bean.valueEnumSet=new HashSet<Enum72>();
		bean.valueEnumSet.add(null);
		bean.valueEnumSet.add(Enum72.VALUE_2);
				
		bean.valueFloatSet=new HashSet<Float>();
		bean.valueFloatSet.add(null);		
		
		bean.setValueIntSet(new HashSet<Integer>());
		bean.getValueIntSet().add(null);
		
		bean.valueLongSet=new HashSet<Long>();
		bean.valueLongSet.add(null);

		bean.valueShortSet=new HashSet<Short>();
		bean.valueShortSet.add(null);

		bean.valueStringSet=new HashSet<String>();
		bean.valueStringSet.add("check1");
		
		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
	
}
