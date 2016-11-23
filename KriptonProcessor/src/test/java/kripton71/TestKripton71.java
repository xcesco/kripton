package kripton71;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

import base.BaseProcessorTest;

public class TestKripton71 extends BaseProcessorTest {
	
	@Before
	public void setup()
	{
		KriptonBinder2.registryBinder(new YamlBinderContext());
		KriptonBinder2.registryBinder(new PropertiesBinderContext());
		KriptonBinder2.registryBinder(new XmlBinderContext());
	}

	@Test
	public void testBeanElement71() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanElement71.class, BeanEnum71.class);
	}
	
	@Test
	public void testBeanAttribute71() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanAttribute71.class, BeanEnum71.class);
	}
	
	@Test
	public void testBeanElement71Compiled() throws IOException, InstantiationException, IllegalAccessException
	{
		//buildBindProcessorTest(BeanElement71.class, BeanEnum71.class);
		//http://www.studytrails.com/java/xml/woodstox/java-xml-stax-woodstox-basic-parsing/
		
		Assert.assertNotNull(new BeanElement71BindMap());
		
		BeanElement71 bean=new BeanElement71();

		
		//bean.valueEnum=BeanEnum.VALUE_2;
		//bean.valueStringList=new ArrayList<String>();
		//bean.valueStringList.add("hello1");
		
		//bean.valueBigDecimalList=new ArrayList<>();
		//bean.valueBigDecimalList.add(BigDecimal.valueOf(1));
		
		bean.valueDoubleList=new ArrayList<>();
		bean.valueDoubleList.add(23.0);
		bean.valueDoubleList.add(null);
		bean.valueDoubleList.add(56.0);
		
		/*
		bean.valueCalendar=Calendar.getInstance();
		bean.valueCurrency=Currency.getInstance(Locale.ITALY);
		bean.valueDate=new Date();
		bean.valueLocale=Locale.ITALY;
		bean.valueTime=new Time(0);
		bean.valueTimeZone=TimeZone.getDefault();
		bean.valueUrl=new URL("http://github.com");
		*/
		
		/*
		bean.id=25;
		bean.valueBean=new BeanElement70();
		bean.valueBean.id=45;
		bean.valueBoolType=true;
		bean.valueBool=true;
		bean.valueByteType=4;
		bean.valueByte=8;
		
		bean.valueShortType=25;
		bean.valueShort=25;
		bean.valueCharType='a';
		bean.valueChar='a';
		
		bean.valueIntType=12;
		bean.valueInt=12;
		bean.valueLongType=24;
		bean.valueLong=24L;
		bean.valueFloatType=24f;
		bean.valueFloat=24f;
		bean.valueDoubleType=24.0;
		bean.valueDouble=24.0;
		*/
		
		//bean.valueString="\"ciao";
		
		//bean.valueCDataString="qq";
		
		serializeAndParse(bean, BinderType.XML);
		serializeAndParse(bean, BinderType.JSON);
		serializeAndParse(bean, BinderType.YAML);
		serializeAndParse(bean, BinderType.PROPERTIES);
	}
	
	@Test
	public void testBeanAttribute71Compiled() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanAttribute71.class);
		//http://www.studytrails.com/java/xml/woodstox/java-xml-stax-woodstox-basic-parsing/
		
		Assert.assertNotNull(new BeanElement71BindMap());
		
		BeanAttribute71 bean=new BeanAttribute71();
		bean.id=25;
		bean.valueBoolType=true;
		bean.valueByteType=45;
		bean.valueCharType='a';
		bean.valueShortType=25;
		bean.valueIntType=12;
		bean.valueLongType=56;
		bean.valueFloatType=1f;
		bean.valueDoubleType=20;
		bean.valueBool=true;
		bean.setValueByte((byte) 45);
		bean.valueChar='a';
		bean.valueShort=25;
		bean.valueInt=12;
		bean.valueLong=56L;
		bean.valueFloat=1f;
		bean.valueDouble=20.0;
		bean.valueString="hello!";
		
		//serializeAndParse(bean, BinderType.XML);
		//serializeAndParse(bean, BinderType.JSON);
		serializeAndParse(bean, BinderType.YAML);
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
