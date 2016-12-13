/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.test03;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.utils.LiteralType;

import sqlite.AbstractBindSQLiteProcessorTest;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class Test03 extends AbstractBindSQLiteProcessorTest {

	/**
	 * No DAO definition with @BindDaoDefinition annotation was found for class
	 * Dummy01DatabaseSchema with @BindDatabaseSchema annotation
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoBean01.class, Bean01.class, Bean02.class);
	}

	@Test
	public void testSelectCursor() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy02DataSource.class, DaoBean02.class, Bean01.class);
	}

	@Test
	public void test02() {
		String values[] = { "long", "long[]", "java.util.List<com.abubusoft.kripton.processor.test03.Bean02>", "java.lang.String",
				"java.util.Map<java.lang.String, com.abubusoft.kripton.processor.test03.Bean02>" };

		for (String item : values) {
			LiteralType type = LiteralType.of(item);

			info("Type: %s, Array: %s, Collection: %s, Primitive: %s, Resolved: %s, List: %s, Map: %s ", type.getRawType(), type.isArray(), type.isCollection(), type.isPrimitive(), type.isResolved(),
					type.isList(), type.isMap());
		}
	}

	private void info(String format, Object... params) {
		logger.info(String.format(format, params));

	}

}
