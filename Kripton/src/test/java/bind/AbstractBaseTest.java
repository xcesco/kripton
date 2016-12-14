package bind;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.abubusoft.kripton.binder.BinderType;
import com.abubusoft.kripton.binder.KriptonBinder;
import com.abubusoft.kripton.binder.context.CborBinderContext;
import com.abubusoft.kripton.binder.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder.context.XmlBinderContext;
import com.abubusoft.kripton.binder.context.YamlBinderContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;

public class AbstractBaseTest {
	private static final String KRIPTON_TEST_DEBUG = "KRIPTON_TEST_DEBUG";

	public boolean display = false;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	protected void check(Object bean, BinderType... checks) {
		int max = 0;
		int[] values = new int[BinderType.values().length];

		boolean all = false;
		List<BinderType> checkList = Arrays.asList(checks);
		if (checks.length == 0)
			all = true;

		int i = 0;
		for (BinderType checkType : BinderType.values()) {
			if (all || checkList.contains(checkType)) {
				if (checkType == BinderType.CBOR) {
					values[i] = serializeAndParseBinary(bean, checkType);
				} else {
					values[i] = serializeAndParse(bean, checkType);
				}
				max = Math.max(max, values[i]);
				i++;
			}
		}

		i = 0;
		for (BinderType checkType : BinderType.values()) {
			if (all || checkList.contains(checkType)) {
				if (display)
					System.out.print(String.format("%s: %s bytes (%.0f%%) ", checkType.toString(), values[i], values[i] * 100.0 / max));
				i++;
			}
		}

		if (display)
			System.out.println();
	}

	protected <E> void checkCollection(Collection<E> collection, Class<E> beanClazz, BinderType... checks) {
		int max = 0;
		int[] values = new int[BinderType.values().length];

		boolean all = false;
		List<BinderType> checkList = Arrays.asList(checks);
		if (checks.length == 0)
			all = true;

		int i = 0;
		for (BinderType checkType : BinderType.values()) {
			if (all || checkList.contains(checkType)) {
				if (checkType == BinderType.CBOR) {
					values[i] = serializeAndParseCollectionBinary(collection, beanClazz, checkType);
				} else {
					values[i] = serializeAndParseCollection(collection, beanClazz, checkType);
				}
				max = Math.max(max, values[i]);
				i++;
			}
		}

		i = 0;
		for (BinderType checkType : BinderType.values()) {
			if (all || checkList.contains(checkType)) {
				if (display)
					System.out.print(String.format("%s: %s bytes (%.0f%%) ", checkType.toString(), values[i], values[i] * 100.0 / max));
				i++;
			}
		}

		if (display)
			System.out.println();
	}

	public <E extends Exception> void expectedException(Class<E> clazzException) throws InstantiationException, IllegalAccessException {
		expectedEx.expect(AssertionError.class);
		expectedEx.expectMessage(clazzException.getSimpleName());
	}

	/**
	 * @param bean
	 * @param type
	 * @return
	 */
	public int serializeAndParse(Object bean, BinderType type) {
		String output1 = KriptonBinder.getBinder(type).serialize(bean);
		if (display)
			System.out.println("[[" + output1 + "]]");

		Object bean2 = KriptonBinder.getBinder(type).parse(output1, bean.getClass());

		String output2 = KriptonBinder.getBinder(type).serialize(bean2);
		if (display) {
			if (output1.equals(output2)) {
				System.out.println("[[-- same --]]");
			} else {
				System.out.println("[[" + output2 + "]]");
			}
		}

		Assert.assertTrue(type.toString(), output1.length() == output2.length());

		ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);

		return output2.length();
	}

	public int serializeAndParseBinary(Object bean, BinderType type) {
		KriptonByteArrayOutputStream bar = new KriptonByteArrayOutputStream();
		KriptonBinder.getBinder(type).serialize(bean, bar);
		String value1 = toString(bar.getByteBufferCopy());

		if (display)
			System.out.println("[[" + value1 + "]]");

		Object bean2 = KriptonBinder.getBinder(type).parse(new ByteArrayInputStream(bar.getByteBuffer()), bean.getClass());

		KriptonByteArrayOutputStream bar2 = new KriptonByteArrayOutputStream();
		KriptonBinder.getBinder(type).serialize(bean2, bar2);
		String value2 = toString(bar2.getByteBufferCopy());

		if (display) {
			if (value1.equals(value2)) {
				System.out.println("[[-- same --]]");
			} else {
				System.out.println("[[" + value2 + "]]");
			}
		}

		Assert.assertTrue(value1.length() == value2.length());
		// ReflectionAssert.assertReflectionEquals(type.toString(), bean,
		// bean2);

		return bar.getCount();
	}

	public <E> int serializeAndParseCollection(Collection<E> list, Class<E> clazz, BinderType type) {
		String value1 = KriptonBinder.getBinder(type).serializeCollection(list, clazz);

		if (display)
			System.out.println("[[" + value1 + "]]");

		Collection<E> list2 = KriptonBinder.getBinder(type).parseCollection(new ArrayList<E>(), clazz, value1);

		String value2 = KriptonBinder.getBinder(type).serializeCollection(list2, clazz);

		if (display) {
			if (value1.equals(value2)) {
				System.out.println("[[-- same --]]");
			} else {
				System.out.println("[[" + value2 + "]]");
			}
		}
		//
		Assert.assertTrue(value1.length() == value2.length());
		ReflectionAssert.assertReflectionEquals(type.toString(), list, list2, ReflectionComparatorMode.LENIENT_ORDER);
		//
		return value1.length();
	}

	public <E> int serializeAndParseCollectionBinary(Collection<E> list, Class<E> clazz, BinderType type) {
		KriptonByteArrayOutputStream bar = new KriptonByteArrayOutputStream();
		KriptonBinder.getBinder(type).serializeCollection(list, clazz, bar);
		String value1 = toString(bar.getByteBuffer());

		if (display)
			System.out.println("[[" + value1 + "]]");

		Collection<E> list2 = KriptonBinder.getBinder(type).parseCollection(new ArrayList<E>(), clazz, bar.getByteBufferCopy());

		KriptonByteArrayOutputStream bar2 = new KriptonByteArrayOutputStream();
		KriptonBinder.getBinder(type).serializeCollection(list2, clazz, bar2);
		String value2 = toString(bar2.getByteBuffer());

		if (display) {
			if (value1.equals(value2)) {
				System.out.println("[[-- same --]]");
			} else {
				System.out.println("[[" + value2 + "]]");
			}
		}

		Assert.assertTrue(value1.length() == value2.length());
		ReflectionAssert.assertReflectionEquals(type.toString(), list, list2, ReflectionComparatorMode.LENIENT_ORDER);
		//
		return bar.getCount();
	}

	@Before
	public void setup() {
		final String value = System.getenv(KRIPTON_TEST_DEBUG);		
		if ("true".equals(value))
		{
			display = true;
		}		
		
		KriptonBinder.registryBinder(new YamlBinderContext());
		KriptonBinder.registryBinder(new PropertiesBinderContext());
		KriptonBinder.registryBinder(new XmlBinderContext());
		KriptonBinder.registryBinder(new CborBinderContext());
	}

	String toString(byte[] input) {
		StringBuilder buffer = new StringBuilder();
		for (int j = 0; j < input.length; j++) {
			buffer.append(String.format("%02X", input[j]));
		}
		return buffer.toString();
	}
}
