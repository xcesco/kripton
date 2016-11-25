package kripton72;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import base.BaseProcessorTest;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

public class TestKripton72 extends BaseProcessorTest {
	
	@Before
	public void setup()
	{
		KriptonBinder2.registryBinder(new YamlBinderContext());
		KriptonBinder2.registryBinder(new PropertiesBinderContext());
		KriptonBinder2.registryBinder(new XmlBinderContext());
	}

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanElement72.class, BeanEnum72.class);
	}
		
	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException
	{
		Assert.assertNotNull(new BeanElement72BindMap());
		
		BeanElement72 bean=new BeanElement72();
			
		bean.valueBeanSet=new HashSet<BeanElement72>();
		bean.valueBeanSet.add(new BeanElement72("hello"));				
		
		bean.valueBigDecimalSet=new HashSet<BigDecimal>();
		bean.valueBigDecimalSet.add(null);
		//bean.valueBigDecimalSet.add(BigDecimal.ONE);
		//bean.valueBigDecimalSet.add(BigDecimal.TEN);
		
		bean.valueBigIntegerSet=new HashSet<BigInteger>();
		bean.valueBigIntegerSet.add(BigInteger.ZERO);
		//bean.valueBigIntegerSet.add(BigInteger.ONE);
		//bean.valueBigIntegerSet.add(BigInteger.TEN);
		//bean.valueBigIntegerSet.add(null);
		
		bean.valueByteSet=null;
		
		bean.valueCharacterSet=new HashSet<Character>();
		//bean.valueCharacterSet.add(null);
		bean.valueCharacterSet.add('a');
		
		bean.valueDoubleSet=new TreeSet<Double>();		
		
		bean.valueEnumSet=new HashSet<BeanEnum72>();
		//bean.valueEnumSet.add(null);
		bean.valueEnumSet.add(BeanEnum72.VALUE_1);
		//bean.valueEnumSet.add(BeanEnum72.VALUE_2);
				
		bean.valueFloatSet=new HashSet<Float>();
		bean.valueFloatSet.add(1f);
		//bean.valueFloatSet.add(2f);
		
		bean.setValueIntSet(new HashSet<Integer>());
		bean.getValueIntSet().add(1);
		//bean.getValueIntSet().add(2);
		
		bean.valueLongSet=new HashSet<Long>();
		bean.valueLongSet.add(null);

		bean.valueShortSet=new HashSet<Short>();
		bean.valueShortSet.add((short)1);
		//bean.valueShortSet.add((short)2);

		bean.valueStringSet=new HashSet<String>();
		bean.valueStringSet.add("check1");
		//bean.valueStringSet.add("check2");
		
		bean.zalueStringFinal="final";
		
		serializeAndParse(bean, BinderType.XML);
		serializeAndParse(bean, BinderType.JSON);
		serializeAndParse(bean, BinderType.YAML);
		serializeAndParse(bean, BinderType.PROPERTIES);
	}

	/**
	 * @param bean
	 * @param type
	 */
	public void serializeAndParse(Object bean, BinderType type) {
		String output1=KriptonBinder2.getBinder(type).serialize(bean);
		System.out.println(output1);
		
		Object bean2=KriptonBinder2.getBinder(type).parse(output1, bean.getClass());				
		
		String output2=KriptonBinder2.getBinder(type).serialize(bean2);
		System.out.println(output2);
		
		Assert.assertTrue(output1.equals(output2));
	}
	
	
}
