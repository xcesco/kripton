package bind.kripton74Map;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class TestKripton74 extends AbstractBindTypeProcessorTest {

	public BeanElement74 createBean() {
		BeanElement74 bean = new BeanElement74();
		
		BeanElement74 bean1 = new BeanElement74();
		bean1.valueString="nice to meet you";

		bean.valueMapStringInteger = new HashMap<>();
		bean.valueMapStringInteger.put("key1", 10);
		bean.valueMapStringInteger.put("key2", 20);
		
		bean.valueMapEnumBean=new HashMap<>();
		bean.valueMapEnumBean.put(BeanEnum74.VALUE_3, null);
		bean.valueMapEnumBean.put(BeanEnum74.VALUE_1, bean1);		
		bean.valueMapEnumBean.put(BeanEnum74.VALUE_2, null);
		
		bean.valueMapIntByteArray=new HashMap<>();
		byte[] a=new byte[23];
		bean.valueMapIntByteArray.put(20, new byte[0]);
		bean.valueMapIntByteArray.put(23, a);
		bean.valueMapIntByteArray.put(27, null);
		
		bean.valueMapBeanLocale=new HashMap<>();
		bean.valueMapBeanLocale.put(new BeanElement74(), Locale.CANADA);
		bean.valueMapBeanLocale.put(new BeanElement74(), null);
		
		//bean.valueString="hello";

		return bean;
	}

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(BeanElement74.class, BeanEnum74.class);
	}

	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException {
		Assert.assertNotNull(new BeanElement74BindMap());

		BeanElement74 bean = createBean();

		check(bean);
	}


}
