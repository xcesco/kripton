/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package bind.feature.kotlin;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;

import bind.AbstractBindTypeProcessorTest;
import bind.feature.kotlin.err1.Article;

/**
 * The Class TestCompile72.
 */
public class TestCompileKotlinErr1 extends AbstractBindTypeProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidDefinition.class, "Class 'bind.feature.kotlin.err1.Article' has no constructor without parameters (to be a mutable class) or with all parameters (to be an immutable class).");
		buildBindProcessorTest(Article.class);
	}

}
