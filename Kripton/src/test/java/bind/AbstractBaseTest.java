package bind;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.output.NullOutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonCborContext;
import com.abubusoft.kripton.KriptonPropertiesContext;
import com.abubusoft.kripton.KriptonYamlContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;

public class AbstractBaseTest {
	private static final String KRIPTON_DEBUG_MODE = "kripton.debug";

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	protected void check(Object bean, BinderType... checks) throws Exception {
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
				System.out.print(String.format("%s: %s bytes (%.0f%%) ", checkType.toString(), values[i], values[i] * 100.0 / max));
				i++;
			}
		}

		System.out.println();
	}

	protected <E> void checkCollection(Collection<E> collection, Class<E> beanClazz, BinderType... checks) throws Exception {
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
				System.out.print(String.format("%s: %s bytes (%.0f%%) ", checkType.toString(), values[i], values[i] * 100.0 / max));
				i++;
			}
		}

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
	 * @throws Exception
	 */
	public int serializeAndParse(Object bean, BinderType type) throws Exception {
		String output1 = KriptonBinder.bind(type).serialize(bean);
		System.out.println("[[" + output1 + "]]");

		Object bean2 = KriptonBinder.bind(type).parse(output1, bean.getClass());

		String output2 = KriptonBinder.bind(type).serialize(bean2);
		if (output1.equals(output2)) {
			System.out.println("[[-- same --]]");
		} else {
			System.out.println("[[" + output2 + "]]");
		}

		Assert.assertTrue(type.toString(), output1.length() == output2.length());

		ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);

		return output2.length();
	}

	public int serializeAndParseBinary(Object bean, BinderType type) throws Exception {
		KriptonByteArrayOutputStream bar = new KriptonByteArrayOutputStream();
		KriptonBinder.bind(type).serialize(bean, bar);
		String value1 = toString(bar.getByteBufferCopy());

		System.out.println("[[" + value1 + "]]");

		Object bean2 = KriptonBinder.bind(type).parse(new ByteArrayInputStream(bar.getByteBuffer()), bean.getClass());

		KriptonByteArrayOutputStream bar2 = new KriptonByteArrayOutputStream();
		KriptonBinder.bind(type).serialize(bean2, bar2);
		String value2 = toString(bar2.getByteBufferCopy());

		if (value1.equals(value2)) {
			System.out.println("[[-- same --]]");
		} else {
			System.out.println("[[" + value2 + "]]");
		}

		Assert.assertTrue(value1.length() == value2.length());

		return bar.getCount();
	}

	public <E> int serializeAndParseCollection(Collection<E> list, Class<E> clazz, BinderType type) throws Exception {
		String value1 = KriptonBinder.bind(type).serializeCollection(list, clazz);

		System.out.println("[[" + value1 + "]]");

		Collection<E> list2 = KriptonBinder.bind(type).parseCollection(new ArrayList<E>(), clazz, value1);

		String value2 = KriptonBinder.bind(type).serializeCollection(list2, clazz);

		if (value1.equals(value2)) {
			System.out.println("[[-- same --]]");
		} else {
			System.out.println("[[" + value2 + "]]");
		}
		//
		Assert.assertTrue(value1.length() == value2.length());
		ReflectionAssert.assertReflectionEquals(type.toString(), list, list2, ReflectionComparatorMode.LENIENT_ORDER);
		//
		return value1.length();
	}

	public <E> int serializeAndParseCollectionBinary(Collection<E> list, Class<E> clazz, BinderType type) throws Exception {
		KriptonByteArrayOutputStream bar = new KriptonByteArrayOutputStream();
		KriptonBinder.bind(type).serializeCollection(list, clazz, bar);
		String value1 = toString(bar.getByteBuffer());

		System.out.println("[[" + value1 + "]]");

		Collection<E> list2 = KriptonBinder.bind(type).parseCollection(new ArrayList<E>(), clazz, bar.getByteBufferCopy());

		KriptonByteArrayOutputStream bar2 = new KriptonByteArrayOutputStream();
		KriptonBinder.bind(type).serializeCollection(list2, clazz, bar2);
		String value2 = toString(bar2.getByteBuffer());

		if (value1.equals(value2)) {
			System.out.println("[[-- same --]]");
		} else {
			System.out.println("[[" + value2 + "]]");
		}

		Assert.assertTrue(value1.length() == value2.length());
		ReflectionAssert.assertReflectionEquals(type.toString(), list, list2, ReflectionComparatorMode.LENIENT_ORDER);
		//
		return bar.getCount();
	}

	@Before
	public void setup() {
		final String value = System.getProperty(KRIPTON_DEBUG_MODE);
		if ("false".equals(value)) {
			// we are in test, but we don't see log on System.out
			System.setOut(new PrintStream(new NullOutputStream()));
			System.setErr(new PrintStream(new NullOutputStream()));
		}

		// when we run junit test, AnnotationProcessor is always in TEST_MODE
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] (%2$s) %5$s %6$s%n");

		KriptonBinder.registryBinder(new KriptonYamlContext());
		KriptonBinder.registryBinder(new KriptonPropertiesContext());
		KriptonBinder.registryBinder(new KriptonCborContext());
	}

	String toString(byte[] input) {
		StringBuilder buffer = new StringBuilder();
		for (int j = 0; j < input.length; j++) {
			buffer.append(String.format("%02X", input[j]));
		}
		return buffer.toString();
	}
}
