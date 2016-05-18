package com.abubusoft.kripton.processor.test03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.abubusoft.kripton.processor.utils.LiteralType;

@RunWith(JUnit4.class)
public class TestBean03 extends BaseProcessorTest {
	
	@Test
	public void test01()
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
