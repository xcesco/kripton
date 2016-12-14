package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Test81ECompile {

	@Test
	public void testBindTransformer()
	{
		assertNull(PrefsTransformer.getLanguageTransform(typeName("java.lang.dummy")));
		assertNull(PrefsTransformer.getMathTransform(typeName("java.lang.dummy")));
		assertNull(PrefsTransformer.getNetTransform(typeName("java.lang.dummy")));
		assertNull(PrefsTransformer.getPrimitiveTransform(typeName("java.lang.dummy")));
		assertNull(PrefsTransformer.getSqlTransform(typeName("java.lang.dummy")));
		assertNull(PrefsTransformer.getUtilTransform(typeName("java.lang.dummy")));
	}
}
