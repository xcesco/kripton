package kripton70;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kripton70.contexts.BinderPropertiesContext;
import kripton70.contexts.BinderYamlContext;
import kripton70.core.BinderType;
import kripton70.core.KriptonLibrary2;

public class Kripton70Test {
	
	@Before
	public void setup()
	{
		KriptonLibrary2.registryBinder(new BinderYamlContext());
		KriptonLibrary2.registryBinder(new BinderPropertiesContext());
	}

	@Test
	public void test() throws IOException
	{
		Bean bean=new Bean();
		bean.id=25;
		bean.description="hello";
		bean.valueByteType=12;
		bean.valueCharType='a';
		bean.valueShortType=13;
		bean.valueBean=new Bean();
		bean.valueBean.id=25;
		bean.valueBean.description="hello";
		
		BinderType type = BinderType.YAML;
		
		String output=KriptonLibrary2.getBinder(type).serialize(bean);
		System.out.println(output);
		
		Bean bean2=KriptonLibrary2.getBinder(type).parse(output, Bean.class);
		
		Assert.assertTrue(bean2.equals(bean));
		
		System.out.println(output);
		
	}
}
