package bind.kripton72;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime72A_1 extends AbstractBaseTest {

	/**
	 * Test all elements filled
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testAllFilledRun() throws IOException, InstantiationException, IllegalAccessException
	{
Assert.assertNotNull(new Bean72ABindMap());
		
		Bean72A bean=new Bean72A();
			
		bean.valueBeanSet=new HashSet<Bean72A>();
		bean.valueBeanSet.add(new Bean72A("helloSet1"));				
		bean.valueBeanSet.add(new Bean72A("helloSet2"));
		bean.valueBeanSet.add(new Bean72A("helloSet3"));
		
		bean.valueBigDecimalSet=new HashSet<BigDecimal>();
		bean.valueBigDecimalSet.add(BigDecimal.ONE);
		bean.valueBigDecimalSet.add(BigDecimal.TEN);
		bean.valueBigDecimalSet.add(BigDecimal.ZERO);
		
		bean.valueBigIntegerSet=new HashSet<BigInteger>();
		bean.valueBigIntegerSet.add(BigInteger.ZERO);
		bean.valueBigIntegerSet.add(BigInteger.TEN);
		bean.valueBigIntegerSet.add(BigInteger.ZERO);
		
		bean.valueByteSet=new HashSet<>();
		bean.valueByteSet.add((byte)1);
		bean.valueByteSet.add((byte)2);
		bean.valueByteSet.add((byte)3);
		
		bean.valueCharacterSet=new HashSet<Character>();
		bean.valueCharacterSet.add('a');
		bean.valueCharacterSet.add('b');
		bean.valueCharacterSet.add('c');
		
		bean.valueDoubleSet=new TreeSet<Double>();		
		bean.valueDoubleSet.add(23.0);
		bean.valueDoubleSet.add(24.0);
		
		bean.valueEnumSet=new HashSet<Enum72>();
		bean.valueEnumSet.add(Enum72.VALUE_1);
		bean.valueEnumSet.add(Enum72.VALUE_2);
				
		bean.valueFloatSet=new HashSet<Float>();
		bean.valueFloatSet.add(1f);		
		
		bean.setValueIntSet(new HashSet<Integer>());
		bean.getValueIntSet().add(1);
		
		bean.valueLongSet=new HashSet<Long>();
		bean.valueLongSet.add(23L);

		bean.valueShortSet=new HashSet<Short>();
		bean.valueShortSet.add((short)1);

		bean.valueStringSet=new HashSet<String>();
		bean.valueStringSet.add("check1");
		
		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
	
}
