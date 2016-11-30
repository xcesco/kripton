package kripton75;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.CborBinderContext;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

import base.BaseProcessorTest;

public class TestKripton75 extends BaseProcessorTest {
	
	@Before
	public void setup() {
		KriptonBinder2.registryBinder(new YamlBinderContext());
		KriptonBinder2.registryBinder(new PropertiesBinderContext());
		KriptonBinder2.registryBinder(new XmlBinderContext());
		KriptonBinder2.registryBinder(new CborBinderContext());
	}

	@Test
	public void testCompile() throws Throwable
	{
		buildBindProcessorTest(BeanElement75.class);
	}
		
	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException
	{
		Assert.assertNotNull(new BeanElement75BindMap());
		
		BeanElement75 bean=new BeanElement75();
			
		bean.valueByteArray=new Byte[2];
		bean.valueByteArray[0]='1';
		bean.valueByteArray[1]='2';
				
		bean.valueByteTypeArray="hello world2!".getBytes();
		
		check(bean);
	}

	
}
