package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Test81DCompile {

	@Test
	public void testBindTransformer()
	{
		assertNull(BindTransformer.getLanguageTransform(typeName("java.lang.dummy")));
		assertNull(BindTransformer.getMathTransform(typeName("java.lang.dummy")));
		assertNull(BindTransformer.getNetTransform(typeName("java.lang.dummy")));
		assertNull(BindTransformer.getPrimitiveTransform(typeName("java.lang.dummy")));
		assertNull(BindTransformer.getSqlTransform(typeName("java.lang.dummy")));
		assertNull(BindTransformer.getUtilTransform(typeName("java.lang.dummy")));
	}
}
