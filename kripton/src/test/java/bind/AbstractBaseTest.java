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
import com.abubusoft.kripton.KriptonSmileContext;
import com.abubusoft.kripton.KriptonYamlContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;


/**
 * The Class AbstractBaseTest.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class AbstractBaseTest {

    /**
     * The Constant KRIPTON_DEBUG_MODE.
     */
    private static final String KRIPTON_DEBUG_MODE = "kripton.debug";

    /**
     * The expected ex.
     */
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    /**
     * Check.
     *
     * @param bean   the bean
     * @param checks the checks
     * @throws Exception the exception
     */
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
     * @param <E>        the element type
     * @param collection the collection
     * @param beanClazz  the bean clazz
     * @param checks     the checks
     * @throws Exception the exception
     */
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
                if (checkType.onlyBinary) {
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
     * Expected exception.
     *
     * @param <E>            the element type
     * @param clazzException the clazz exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    public <E extends Exception> void expectedException(Class<E> clazzException) throws InstantiationException, IllegalAccessException {
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(clazzException.getSimpleName());
    }

    /**
     * Serialize and parse.
     *
     * @param bean the bean
     * @param type the type
     * @return serialize and parse
     * @throws Exception the exception
     */
    public int serializeAndParse(Object bean, BinderType type) throws Exception {
        String output1 = KriptonBinder.bind(type).serialize(bean);

        System.out.println(String.format("Data format: %s, value = [[%s]]", type, output1));
        Object bean2 = KriptonBinder.bind(type).parse(output1, bean.getClass());

        String output2 = KriptonBinder.bind(type).serialize(bean2);
        if (output1.equals(output2)) {
            System.out.println("[[-- same --]]");
        } else {
            System.out.println("[[" + output2 + "]]");
        }


        System.out.print(String.format("Data result: %s", type));
        Assert.assertEquals(type.toString(), output1.length(), output2.length());
        ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);
        System.out.println(String.format(" ... OK"));

        return output2.length();
    }

    /**
     * Serialize and parse binary.
     *
     * @param bean the bean
     * @param type the type
     * @return int
     * @throws Exception the exception
     */
    public int serializeAndParseBinary(Object bean, BinderType type) throws Exception {
        KriptonByteArrayOutputStream bar = new KriptonByteArrayOutputStream();
        KriptonBinder.bind(type).serialize(bean, bar);
        String value1 = toString(bar.getByteBufferCopy());

        System.out.println(String.format("Data format: %s, value = [[%s]]", type, value1));

        Object bean2 = KriptonBinder.bind(type).parse(new ByteArrayInputStream(bar.getByteBuffer()), bean.getClass());

        KriptonByteArrayOutputStream bar2 = new KriptonByteArrayOutputStream();
        KriptonBinder.bind(type).serialize(bean2, bar2);
        String value2 = toString(bar2.getByteBufferCopy());

        if (value1.equals(value2)) {
            System.out.println("[[-- same --]]");
        } else {
            System.out.println("[[" + value2 + "]]");
        }

        Assert.assertEquals(type.toString(), value1.length(), value2.length());

        return bar.getCount();
    }

    /**
     * Serialize and parse collection.
     *
     * @param <E>   the element type
     * @param list  the list
     * @param clazz the clazz
     * @param type  the type
     * @return int
     * @throws Exception the exception
     */
    public <E> int serializeAndParseCollection(Collection<E> list, Class<E> clazz, BinderType type) throws Exception {
        String value1 = KriptonBinder.bind(type).serializeCollection(list, clazz);

        System.out.println(String.format("Data format: %s, value = [[%s]]", type, value1));

        Collection<E> list2 = KriptonBinder.bind(type).parseCollection(value1, new ArrayList<E>(), clazz);

        String value2 = KriptonBinder.bind(type).serializeCollection(list2, clazz);

        if (value1.equals(value2)) {
            System.out.println("[[-- same --]]");
        } else {
            System.out.println("[[" + value2 + "]]");
        }
        //
        Assert.assertEquals(type.toString(), value1.length(), value2.length());
        ReflectionAssert.assertReflectionEquals(type.toString(), list, list2, ReflectionComparatorMode.LENIENT_ORDER);
        //
        return value1.length();
    }

    /**
     * Serialize and parse collection binary.
     *
     * @param <E>   the element type
     * @param list  the list
     * @param clazz the clazz
     * @param type  the type
     * @return int
     * @throws Exception the exception
     */
    public <E> int serializeAndParseCollectionBinary(Collection<E> list, Class<E> clazz, BinderType type) throws Exception {
        KriptonByteArrayOutputStream bar = new KriptonByteArrayOutputStream();
        KriptonBinder.bind(type).serializeCollection(list, clazz, bar);
        String value1 = toString(bar.getByteBuffer());

        System.out.println(String.format("Data format: %s, value = [[%s]]", type, value1));

        Collection<E> list2 = KriptonBinder.bind(type).parseCollection(bar.getByteBufferCopy(), new ArrayList<E>(), clazz);

        KriptonByteArrayOutputStream bar2 = new KriptonByteArrayOutputStream();
        KriptonBinder.bind(type).serializeCollection(list2, clazz, bar2);
        String value2 = toString(bar2.getByteBuffer());

        if (value1.equals(value2)) {
            System.out.println("[[-- same --]]");
        } else {
            System.out.println("[[" + value2 + "]]");
        }

        Assert.assertEquals(type.toString(), value1.length(), value2.length());
        ReflectionAssert.assertReflectionEquals(type.toString(), list, list2, ReflectionComparatorMode.LENIENT_ORDER);
        //
        return bar.getCount();
    }

    /**
     * Setup.
     */
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
        KriptonBinder.registryBinder(new KriptonSmileContext());
    }

    /**
     * Log.
     *
     * @param format the format
     * @param args   the args
     */
    public void log(String format, Object... args) {
        System.out.println(String.format(format, args));
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
