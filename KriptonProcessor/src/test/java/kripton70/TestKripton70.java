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
	public void test() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(Bean70.class);
	}
	
	@Test
	public void testCompiled() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(Bean70.class);
		//http://www.studytrails.com/java/xml/woodstox/java-xml-stax-woodstox-basic-parsing/
		
		Assert.assertNotNull(new Bean70BindMap());
		
		Bean70 bean=new Bean70();
		bean.id=25;
		bean.valueBoolType=true;
		bean.valueBool2=true;
		bean.valueString="\"benvenuto\"}\ncome va";
		
		BinderType type = BinderType.XML;
		
		String output=KriptonBinder2.getBinder(type).serialize(bean);
		System.out.println(output);
		
		Bean70 bean2=KriptonBinder2.getBinder(type).parse(output, Bean70.class);
		
		Assert.assertTrue(bean2.equals(bean));
		
		System.out.println(output);
	
	}
	
	
}
