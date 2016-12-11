package bind.kripton77;

import org.junit.Test;

import com.abubusoft.kripton.processor.bind.transform.ListBindTransformation;
import com.abubusoft.kripton.processor.bind.transform.SetBindTransformation;
import com.abubusoft.kripton.processor.exceptions.KriptonClassNotFoundException;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import bind.AbstractBindTypeProcessorTest;

public class Test77 extends AbstractBindTypeProcessorTest {
	
	public class TestListTransform extends ListBindTransformation
	{

		public TestListTransform(ParameterizedTypeName clazz) {
			super(clazz);		
		}
		
		public Class<?> testDefineCollectionClass(ParameterizedTypeName collectionTypeName)
		{
			return this.defineCollectionClass(collectionTypeName);
		}
		
	}
	
	public class TestSetTransform extends SetBindTransformation
	{

		public TestSetTransform(ParameterizedTypeName clazz) {
			super(clazz);		
		}
		
		public Class<?> testDefineCollectionClass(ParameterizedTypeName collectionTypeName)
		{
			return this.defineCollectionClass(collectionTypeName);
		}
		
	}

	/**
	 * Test class not found exception on list transformation
	 */
	@Test(expected=KriptonClassNotFoundException.class)
	public void testTestListTransformException(){
		
		TestListTransform test=new TestListTransform(ParameterizedTypeName.get(ClassName.get("org.dummy", "Collection"), TypeName.OBJECT));
		
		test.testDefineCollectionClass(ParameterizedTypeName.get(ClassName.get("org.dummy", "Crash"), TypeName.OBJECT));
	}
	
	/**
	 * Test class not found exception on set transformation
	 */
	@Test(expected=KriptonClassNotFoundException.class)
	public void testTestSetTransformException(){
		
		TestSetTransform test=new TestSetTransform(ParameterizedTypeName.get(ClassName.get("org.dummy", "Collection"), TypeName.OBJECT));
		
		test.testDefineCollectionClass(ParameterizedTypeName.get(ClassName.get("org.dummy", "Crash"), TypeName.OBJECT));
	}
	


}
