package kripton70;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

import base.BaseProcessorTest;

public class TestKripton70 extends BaseProcessorTest {
	
	@Before
	public void setup()
	{
		KriptonBinder2.registryBinder(new YamlBinderContext());
		KriptonBinder2.registryBinder(new PropertiesBinderContext());
		KriptonBinder2.registryBinder(new XmlBinderContext());
	}

	@Test
	public void testBeanElement70() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanElement70.class);
	}
	
	@Test
	public void testBeanAttribute70() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanAttribute70.class);
	}
	
	@Test
	public void testBeanElement70Compiled() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanElement70.class);
		//http://www.studytrails.com/java/xml/woodstox/java-xml-stax-woodstox-basic-parsing/
		
		Assert.assertNotNull(new BeanElement70BindMap());
		
		BeanElement70 bean=new BeanElement70();
		bean.id=25;
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
		bean.valueString="\"ciao";
		
		//serializeAndParse(bean, BinderType.XML);
		serializeAndParse(bean, BinderType.JSON);
	}
	
	@Test
	public void testBeanAttribute70Compiled() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanAttribute70.class);
		//http://www.studytrails.com/java/xml/woodstox/java-xml-stax-woodstox-basic-parsing/
		
		Assert.assertNotNull(new BeanElement70BindMap());
		
		BeanAttribute70 bean=new BeanAttribute70();
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
		serializeAndParse(bean, BinderType.JSON);
	
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
