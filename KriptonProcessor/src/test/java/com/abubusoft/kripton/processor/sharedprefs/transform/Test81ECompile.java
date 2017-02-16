package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Test81ECompile {

	@Test
	public void testBindTransformer()
	{
		assertEquals(StringPrefsTransform.class.getName(), PrefsTransformer.getLanguageTransform(typeName("java.lang.String")).getClass().getName());
		assertEquals(BigDecimalPrefsTransform.class.getName(), PrefsTransformer.getMathTransform(typeName("java.math.BigDecimal")).getClass().getName());
		assertEquals(UrlPrefsTransform.class.getName(), PrefsTransformer.getNetTransform(typeName("java.net.URL")).getClass().getName());
		assertEquals(IntegerPrefsTransform.class.getName(), PrefsTransformer.getPrimitiveTransform(typeName("int")).getClass().getName());
		assertEquals(TimePrefsTransform.class.getName(), PrefsTransformer.getSqlTransform(typeName("java.sql.Time")).getClass().getName());
		assertEquals(DatePrefsTransform.class.getName(), PrefsTransformer.getUtilTransform(typeName("java.util.Date")).getClass().getName());
	}
}
