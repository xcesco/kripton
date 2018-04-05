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
package bind.kripton77;

import org.junit.Test;

import com.abubusoft.kripton.processor.bind.transform.ListBindTransformation;
import com.abubusoft.kripton.processor.bind.transform.SetBindTransformation;
import com.abubusoft.kripton.processor.exceptions.KriptonClassNotFoundException;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import bind.AbstractBindTypeProcessorTest;

// TODO: Auto-generated Javadoc
/**
 * The Class Test77.
 */
public class Test77 extends AbstractBindTypeProcessorTest {
	
	/**
	 * The Class TestListTransform.
	 */
	public class TestListTransform extends ListBindTransformation
	{

		/**
		 * Instantiates a new test list transform.
		 *
		 * @param clazz the clazz
		 */
		public TestListTransform(ParameterizedTypeName clazz) {
			super(clazz);		
		}
		
		/**
		 * Test define collection class.
		 *
		 * @param collectionTypeName the collection type name
		 * @return the class
		 */
		public Class<?> testDefineCollectionClass(ParameterizedTypeName collectionTypeName)
		{
			return this.defineCollectionClass(collectionTypeName);
		}
		
	}
	
	/**
	 * The Class TestSetTransform.
	 */
	public class TestSetTransform extends SetBindTransformation
	{

		/**
		 * Instantiates a new test set transform.
		 *
		 * @param clazz the clazz
		 */
		public TestSetTransform(ParameterizedTypeName clazz) {
			super(clazz);		
		}
		
		/**
		 * Test define collection class.
		 *
		 * @param collectionTypeName the collection type name
		 * @return the class
		 */
		public Class<?> testDefineCollectionClass(ParameterizedTypeName collectionTypeName)
		{
			return this.defineCollectionClass(collectionTypeName);
		}
		
	}

	/**
	 * Test class not found exception on list transformation.
	 */
	@Test(expected=KriptonClassNotFoundException.class)
	public void testTestListTransformException(){
		
		TestListTransform test=new TestListTransform(ParameterizedTypeName.get(ClassName.get("org.dummy", "Collection"), TypeName.OBJECT));
		
		test.testDefineCollectionClass(ParameterizedTypeName.get(ClassName.get("org.dummy", "Crash"), TypeName.OBJECT));
	}
	
	/**
	 * Test class not found exception on set transformation.
	 */
	@Test(expected=KriptonClassNotFoundException.class)
	public void testTestSetTransformException(){
		
		TestSetTransform test=new TestSetTransform(ParameterizedTypeName.get(ClassName.get("org.dummy", "Collection"), TypeName.OBJECT));
		
		test.testDefineCollectionClass(ParameterizedTypeName.get(ClassName.get("org.dummy", "Crash"), TypeName.OBJECT));
	}
	


}
