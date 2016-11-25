package kripton73;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

import base.BaseProcessorTest;

public class TestKripton73 extends BaseProcessorTest {
	
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
		buildBindProcessorTest(BeanElement73.class, BeanEnum73.class);
	}
		
	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException
	{
		Assert.assertNotNull(new BeanElement73BindMap());
		
		BeanElement73 bean=new BeanElement73();
			
		bean.valueBeanArray=new BeanElement73[1];
		bean.valueBeanArray[0]=new BeanElement73("hello");				
		
		bean.valueBigDecimalArray=new BigDecimal[4];
		bean.valueBigDecimalArray[0]=null;
		bean.valueBigDecimalArray[1]=BigDecimal.ONE;
		bean.valueBigDecimalArray[2]=BigDecimal.TEN;
		bean.valueBigDecimalArray[3]=null;		
		
		bean.valueBigIntegerArray=new BigInteger[5];
		bean.valueBigIntegerArray[0]=null;
		bean.valueBigIntegerArray[1]=BigInteger.ZERO;
		bean.valueBigIntegerArray[2]=BigInteger.ONE;
		bean.valueBigIntegerArray[3]=BigInteger.TEN;
		bean.valueBigIntegerArray[4]=null;
		
		bean.valueByteArray=null;
		
		bean.valueCharacterArray=new Character[2];
		bean.valueCharacterArray[0]=null;
		bean.valueCharacterArray[1]='a';
		
		bean.valueDoubleArray=new Double[0];		
		
		bean.valueEnumArray=new BeanEnum73[3];
		bean.valueEnumArray[0]=null;
		bean.valueEnumArray[1]=BeanEnum73.VALUE_1;
		bean.valueEnumArray[2]=BeanEnum73.VALUE_2;
				
		bean.valueFloatArray=new Float[2];
		bean.valueFloatArray[0]=1f;
		bean.valueFloatArray[1]=2f;
		
		bean.setValueIntArray(new Integer[2]);
		bean.getValueIntArray()[0]=1;
		bean.getValueIntArray()[1]=2;
		
		bean.valueLongArray=new Long[2];
		bean.valueLongArray[0]=null;
		bean.valueLongArray[1]=null;

		bean.valueShortArray=new Short[2];
		bean.valueShortArray[0]=(short)1;
		bean.valueShortArray[1]=(short)2;

		bean.valueStringArray=new String[2];
		bean.valueStringArray[0]="check1";
		bean.valueStringArray[1]="check2";
		
//		bean.zalueStringFinal="final";
		
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
