package com.abubusoft.kripton.processor.test03;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.abubusoft.kripton.processor.utils.LiteralType;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class Test03 extends BaseProcessorTest {

	/**
	 *  No DAO definition with @BindDaoDefinition annotation was found for class Dummy01DatabaseSchema with @BindDatabaseSchema annotation
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoBean01.class, Bean01.class, Bean02.class);
	}
	
	@Test
	public void testSelectCursor() throws IOException, InstantiationException, IllegalAccessException
	{
		buildDataSourceProcessorTest(Dummy02DataSource.class, DaoBean02.class, Bean01.class);
	}
	
	@Test
	public void test02()
	{
		String values[]={"long", "long[]","java.util.List<com.abubusoft.kripton.processor.test03.Bean02>","java.lang.String", "java.util.Map<java.lang.String, com.abubusoft.kripton.processor.test03.Bean02>"};
		
		for (String item: values)
		{
			LiteralType type=LiteralType.of(item);
			
			info("Type: %s, Array: %s, Collection: %s, Primitive: %s, Resolved: %s, List: %s, Map: %s ", type.getRawType(), type.isArray(), type.isCollection(), type.isPrimitive(), type.isResolved(), type.isList(), type.isMap());
		}
	}

	private void info(String format, Object ... params) {
		logger.info(String.format(format, params));
		
	}

}
