package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Test81FCompile {

	@Test
	public void testBindTransformer()
	{
		assertEquals(StringSQLTransform.class.getName(), SQLTransformer.getLanguageTransform(typeName("java.lang.String")).getClass().getName());
		assertEquals(BigDecimalSQLTransform.class.getName(), SQLTransformer.getMathTransform(typeName("java.math.BigDecimal")).getClass().getName());
		assertEquals(UrlSQLTransform.class.getName(), SQLTransformer.getNetTransform(typeName("java.net.URL")).getClass().getName());
		assertEquals(IntegerSQLTransform.class.getName(), SQLTransformer.getPrimitiveTransform(typeName("int")).getClass().getName());
		assertEquals(TimeSQLTransform.class.getName(), SQLTransformer.getSqlTransform(typeName("java.sql.Time")).getClass().getName());
		assertEquals(DateSQLTransform.class.getName(), SQLTransformer.getUtilTransform(typeName("java.util.Date")).getClass().getName());
	}
}
