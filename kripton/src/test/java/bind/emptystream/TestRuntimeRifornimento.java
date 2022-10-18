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
package bind.emptystream;

import bind.AbstractBaseTest;
import bind.bindenum.Bean;
import bind.bindenum.LogLevel;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import org.apache.commons.lang.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


/**
 * The Class TestRuntimeEnum.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestRuntimeRifornimento extends AbstractBaseTest {

    /**
     * Test run.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRun() throws Exception {
        String input = "";
        Rifornimento result = KriptonBinder.jsonBind().parse(input, Rifornimento.class);
        assertTrue(result == null);
        //   assertThat(result, hasNoNullFieldsOrProperties());
    }

    @Test
    public void testRunNull() throws Exception {
        String input = null;
        Rifornimento result = KriptonBinder.jsonBind().parse(input, Rifornimento.class);
        assertTrue(result == null);
    }

    @Test
    public void testRunEmptyString() throws Exception {
        String input = "";
        Rifornimento result = KriptonBinder.jsonBind().parse(input, Rifornimento.class);
        assertTrue(result == null);
    }

    @Test
    public void testRunEmptyReader() throws Exception {
        String input = "";
        Rifornimento result = KriptonBinder.jsonBind().parse(new StringReader(input), Rifornimento.class);
        assertTrue(result == null);
        List<Rifornimento> resultList = KriptonBinder.jsonBind().parseList(new StringReader(input), Rifornimento.class);
        assertTrue(resultList.isEmpty());

    }

    @Test
    public void testRunXml() throws Exception {
        String input = "";
        Rifornimento result = KriptonBinder.xmlBind().parse(input, Rifornimento.class);
        assertTrue(result == null);

        Assert.assertThrows(KriptonRuntimeException.class, () -> {
            List<Rifornimento> resultList = KriptonBinder.xmlBind().parseCollection(new StringReader(input), new ArrayList<>(), Rifornimento.class);
            assertTrue(resultList == null);
        });

    }

    @Test
    public void testSimpleRunEmpty() throws Exception {
        String input = "";
        SimpleRifornimento result = KriptonBinder.jsonBind().parse(input, SimpleRifornimento.class);
        assertTrue(result == null);
    }

    @Test
    public void testSimpleRunEmptyXml() throws Exception {
        String input = "";
        SimpleRifornimento result = KriptonBinder.xmlBind().parse(input, SimpleRifornimento.class);
        assertTrue(result == null);
    }


}
