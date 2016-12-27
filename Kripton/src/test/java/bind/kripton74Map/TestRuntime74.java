package bind.kripton74Map;

import java.util.HashMap;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime74 extends AbstractBaseTest {

	public Bean74 createBean() {
		Bean74 bean = new Bean74();
		
		Bean74 bean1 = new Bean74();
		bean1.valueString="nice to meet you";

		bean.valueMapStringInteger = new HashMap<>();
		bean.valueMapStringInteger.put("key1", 10);
		bean.valueMapStringInteger.put("key2", 20);
		
		bean.valueMapEnumBean=new HashMap<>();
		bean.valueMapEnumBean.put(Enum74.VALUE_3, null);
		bean.valueMapEnumBean.put(Enum74.VALUE_1, bean1);		
		bean.valueMapEnumBean.put(Enum74.VALUE_2, null);
		
		bean.valueMapIntByteArray=new HashMap<>();
		byte[] a=new byte[23];
		bean.valueMapIntByteArray.put(20, new byte[0]);
		bean.valueMapIntByteArray.put(23, a);
		bean.valueMapIntByteArray.put(27, null);
		
		bean.valueMapBeanLocale=new HashMap<>();
		bean.valueMapBeanLocale.put(new Bean74(), Locale.CANADA);
		bean.valueMapBeanLocale.put(new Bean74(), null);
		
		//bean.valueString="hello";

		return bean;
	}

	@Test
	public void testRun() throws Exception {
		Assert.assertNotNull(new Bean74BindMap());

		Bean74 bean = createBean();

		check(bean);
	}


}
