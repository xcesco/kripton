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
package bind.git49;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.KriptonNoAnnotatedClassException;

import bind.AbstractBindTypeProcessorTest;


/**
 * Test bean field.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestCompileGit49 extends AbstractBindTypeProcessorTest {

	
	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(KriptonNoAnnotatedClassException.class, "'bind.git49.Bean3'");
		buildBindProcessorTest(Bean1.class, Bean2.class, Bean3.class, Bean3Adapter.class);
	}

}
