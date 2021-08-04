/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package bind;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonCborContext;
import com.abubusoft.kripton.KriptonPropertiesContext;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.KriptonYamlContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.processor.BaseProcessor;

import base.BaseProcessorTest;
import edu.emory.mathcs.backport.java.util.Arrays;


/**
 * The Class AbstractBindTypeProcessorTest.
 */
public abstract class AbstractBindTypeProcessorTest extends BaseProcessorTest {

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		KriptonBinder.registryBinder(new KriptonYamlContext());
		KriptonBinder.registryBinder(new KriptonPropertiesContext());
		KriptonBinder.registryBinder(new KriptonXmlContext());
		KriptonBinder.registryBinder(new KriptonCborContext());

		if (BaseProcessor.DEBUG_MODE) {
			testType = TestType.PREPARE_TEST_JAVA_LIBRARY;
			destinationPath = PathSourceType.DEST_TEST_JAVA_LIBRARY;
		} else {
			testType = TestType.NONE;
		}
	}

	/**
	 * Check.
	 *
	 * @param bean the bean
	 * @param checks the checks
	 * @throws Exception the exception
	 */
	protected void check(Object bean, BinderType... checks) throws Exception {
		int max = 0;
		int[] values = new int[BinderType.values().length];

		boolean all = false;
		@SuppressWarnings("unchecked")
		List<BinderType> checkList = Arrays.asList(checks);
		if (checks.length == 0)
			all = true;

		int i = 0;
		for (BinderType checkType : BinderType.values()) {
			if (all || checkList.contains(checkType)) {
				if (checkType.onlyBinary) {
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

	/**
	 * Check collection.
	 *
	 * @param <E> the element type
	 * @param collection the collection
	 * @param beanClazz the bean clazz
	 * @param checks the checks
	 * @throws Exception the exception
	 */
	protected <E> void checkCollection(Collection<E> collection, Class<E> beanClazz, BinderType... checks) throws Exception {
		int max = 0;
		int[] values = new int[BinderType.values().length];

		boolean all = false;
		@SuppressWarnings("unchecked")
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

	/**
	 * Serialize and parse.
	 *
	 * @param bean the bean
	 * @param type the type
	 * @return the int
	 * @throws Exception the exception
	 */
	public int serializeAndParse(Object bean, BinderType type) throws Exception {
		String output1 = KriptonBinder.bind(type).serialize(bean);
		System.out.println(output1);

		Object bean2 = KriptonBinder.bind(type).parse(output1, bean.getClass());

		String output2 = KriptonBinder.bind(type).serialize(bean2);
		System.out.println(output2);

		Assert.assertEquals(type.toString(),output1.length(), output2.length());

		ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);

		return output2.length();
	}

	/**
	 * Serialize and parse collection.
	 *
	 * @param <E> the element type
	 * @param list the list
	 * @param clazz the clazz
	 * @param type the type
	 * @return the int
	 * @throws Exception the exception
	 */
	public <E> int serializeAndParseCollection(Collection<E> list, Class<E> clazz, BinderType type) throws Exception {
		String value1 = KriptonBinder.bind(type).serializeCollection(list, clazz);
		Collection<E> list2 = KriptonBinder.bind(type).parseCollection(value1, new ArrayList<E>(), clazz);

		String value2 = KriptonBinder.bind(type).serializeCollection(list2, clazz);

		System.out.println(value1);
		System.out.println(value2);
		//
		Assert.assertEquals(type.toString(),value1.length(), value2.length());
		ReflectionAssert.assertReflectionEquals(type.toString(), list, list2, ReflectionComparatorMode.LENIENT_ORDER);
		//
		return value1.length();
	}

	/**
	 * Serialize and parse collection binary.
	 *
	 * @param <E> the element type
	 * @param list the list
	 * @param clazz the clazz
	 * @param type the type
	 * @return the int
	 * @throws Exception the exception
	 */
	public <E> int serializeAndParseCollectionBinary(Collection<E> list, Class<E> clazz, BinderType type) throws Exception {
		KriptonByteArrayOutputStream bar = new KriptonByteArrayOutputStream();
		KriptonBinder.bind(type).serializeCollection(list, clazz, bar);
		String value1 = toString(bar.getByteBuffer());

		Collection<E> list2 = KriptonBinder.bind(type).parseCollection(bar.getByteBufferCopy(), new ArrayList<E>(), clazz);

		KriptonByteArrayOutputStream bar2 = new KriptonByteArrayOutputStream();
		KriptonBinder.bind(type).serializeCollection(list2, clazz, bar2);
		String value2 = toString(bar2.getByteBuffer());

		System.out.println(value1);
		System.out.println(value2);

		Assert.assertEquals(type.toString(),value1.length(), value2.length());
		ReflectionAssert.assertReflectionEquals(type.toString(), list, list2, ReflectionComparatorMode.LENIENT_ORDER);
		//
		return bar.getCount();
	}

	/**
	 * Serialize and parse binary.
	 *
	 * @param bean the bean
	 * @param type the type
	 * @return the int
	 * @throws Exception the exception
	 */
	public int serializeAndParseBinary(Object bean, BinderType type) throws Exception {
		KriptonByteArrayOutputStream bar = new KriptonByteArrayOutputStream();
		KriptonBinder.bind(type).serialize(bean, bar);
		String value1 = toString(bar.getByteBuffer());

		System.out.println(value1);
		Object bean2 = KriptonBinder.bind(type).parse(new ByteArrayInputStream(bar.getByteBuffer()), bean.getClass());

		KriptonByteArrayOutputStream bar2 = new KriptonByteArrayOutputStream();
		KriptonBinder.bind(type).serialize(bean2, bar2);
		String value2 = toString(bar2.getByteBuffer());
		System.out.println(value2);

		Assert.assertEquals(type.toString(),value1.length(), value2.length());

		return bar.getCount();
	}

	/**
	 * To string.
	 *
	 * @param input the input
	 * @return the string
	 */
	String toString(byte[] input) {
		StringBuilder buffer = new StringBuilder();
		for (int j = 0; j < input.length; j++) {
			buffer.append(String.format("%02X", input[j]));
		}
		return buffer.toString();
	}
}
