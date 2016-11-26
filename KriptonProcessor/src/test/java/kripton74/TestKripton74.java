package kripton74;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

import all.BinderFactoryTest;
import base.BaseProcessorTest;

public class TestKripton74 extends BaseProcessorTest {

	public BeanElement74 createBean() {
		BeanElement74 bean = new BeanElement74();

		bean.valueMapStringInteger = new HashMap<>();
		bean.valueMapStringInteger.put("key1", 10);
		bean.valueMapStringInteger.put("key2", 20);

		return bean;
	}

	@Before
	public void setup() {
		KriptonBinder2.registryBinder(new YamlBinderContext());
		KriptonBinder2.registryBinder(new PropertiesBinderContext());
		KriptonBinder2.registryBinder(new XmlBinderContext());
	}

	@Test
	public void testCompatibility() throws IOException, InstantiationException, IllegalAccessException {
		BeanElement74 bean = createBean();
		{
			BinderWriter writer = KriptonBinder.getXmlWriter();
			//System.out.println(writer.write(bean));
		}
		{
			BinderWriter writer = KriptonBinder.getJsonWriter();
			System.out.println(writer.write(bean));
		}
	}

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(BeanElement74.class);
	}

	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException {
		Assert.assertNotNull(new BeanElement74BindMap());

		BeanElement74 bean = createBean();

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
		String output1 = KriptonBinder2.getBinder(type).serialize(bean);
		System.out.println(output1);

		Object bean2 = KriptonBinder2.getBinder(type).parse(output1, bean.getClass());

		String output2 = KriptonBinder2.getBinder(type).serialize(bean2);
		System.out.println(output2);

		Assert.assertTrue(output1.equals(output2));
	}

}
