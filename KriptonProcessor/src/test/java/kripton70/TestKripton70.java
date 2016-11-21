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
	public void testCompiled() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanElement70.class);
		//http://www.studytrails.com/java/xml/woodstox/java-xml-stax-woodstox-basic-parsing/
		
		Assert.assertNotNull(new Bean70BindMap());
		
		BeanElement70 bean=new BeanElement70();
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
	/*	bean.valueBool2=true;
		bean.valueAttributeString="\"benvenuto\"}\ncome va";
		bean.valueCDataString="this CDATA";
		bean.valueElementString="this element";*/
		
		BinderType type = BinderType.XML;
		
		String output=KriptonBinder2.getBinder(type).serialize(bean);
		System.out.println(output);
		
		BeanElement70 bean2=KriptonBinder2.getBinder(type).parse(output, BeanElement70.class);
		
		Assert.assertTrue(bean2.equals(bean));
		
		System.out.println(output);
	
	}
	
	
}
