package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Test81FCompile {

	@Test
	public void testBindTransformer()
	{
		assertNull(SQLTransformer.getLanguageTransform(typeName("java.lang.dummy")));
		assertNull(SQLTransformer.getMathTransform(typeName("java.lang.dummy")));
		assertNull(SQLTransformer.getNetTransform(typeName("java.lang.dummy")));
		assertNull(SQLTransformer.getPrimitiveTransform(typeName("java.lang.dummy")));
		assertNull(SQLTransformer.getSqlTransform(typeName("java.lang.dummy")));
		assertNull(SQLTransformer.getUtilTransform(typeName("java.lang.dummy")));
	}
}
