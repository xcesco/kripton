package bind.kripton72;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime72 extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception
	{
		Assert.assertNotNull(new Bean72BindMap());
		
		Bean72 bean=new Bean72();
			
		bean.valueBeanSet=new HashSet<Bean72>();
		bean.valueBeanSet.add(new Bean72("hello"));				
		
		bean.valueBigDecimalSet=new HashSet<BigDecimal>();
		bean.valueBigDecimalSet.add(null);
		
		bean.valueBigIntegerSet=new HashSet<BigInteger>();
		bean.valueBigIntegerSet.add(BigInteger.ZERO);
		
		bean.valueByteSet=null;
		
		bean.valueCharacterSet=new HashSet<Character>();
		bean.valueCharacterSet.add('a');
		
		bean.valueDoubleSet=new TreeSet<Double>();		
		
		bean.valueEnumSet=new HashSet<Enum72>();
		bean.valueEnumSet.add(Enum72.VALUE_1);
				
		bean.valueFloatSet=new HashSet<Float>();
		bean.valueFloatSet.add(1f);
		
		bean.setValueIntSet(new HashSet<Integer>());
		bean.getValueIntSet().add(1);
		
		bean.valueLongSet=new HashSet<Long>();
		bean.valueLongSet.add(null);

		bean.valueShortSet=new HashSet<Short>();
		bean.valueShortSet.add((short)1);

		bean.valueStringSet=new HashSet<String>();
		bean.valueStringSet.add("check1");
		
		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
	
}
