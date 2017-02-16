package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Test81DCompile {

	@Test
	public void testBindTransformer()
	{
		assertEquals(StringBindTransform.class.getName(), BindTransformer.getLanguageTransform(typeName("java.lang.String")).getClass().getName());
		assertEquals(BigDecimalBindTransform.class.getName(), BindTransformer.getMathTransform(typeName("java.math.BigDecimal")).getClass().getName());
		assertEquals(UrlBindTransform.class.getName(), BindTransformer.getNetTransform(typeName("java.net.URL")).getClass().getName());
		assertEquals(IntegerBindTransform.class.getName(), BindTransformer.getPrimitiveTransform(typeName("int")).getClass().getName());
		assertEquals(TimeBindTransform.class.getName(), BindTransformer.getSqlTransform(typeName("java.sql.Time")).getClass().getName());
		assertEquals(DateBindTransform.class.getName(), BindTransformer.getUtilTransform(typeName("java.util.Date")).getClass().getName());
	}
}
