package bind.kripton80ContextCollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.BinderType;

import bind.AbstractBaseTest;

public class TestRuntime80A extends AbstractBaseTest {

	@Test
	public void testRun_1() throws Exception {
		Assert.assertNotNull(new Bean80ABindMap());
				
		List<Bean80A> list=new ArrayList<>();
		list.add(createBean());
		list.add(new Bean80A());
		
		checkCollection(list, Bean80A.class, BinderType.JSON, BinderType.CBOR, BinderType.YAML, BinderType.PROPERTIES);
	}
	
	@Test
	public void testRun_2() throws Exception {
		Assert.assertNotNull(new Bean80ABindMap());
		
		check(createBean());		
		check(new Bean80A());		
	}
	
	public Bean80A createBean()
	{
		Bean80A bean = new Bean80A();

		bean.id = 25;
		bean.valueBean = new Bean80A();
		bean.valueBean.id = 45;
		bean.valueString = "\"ciao";
		
		return bean;
	}

}
