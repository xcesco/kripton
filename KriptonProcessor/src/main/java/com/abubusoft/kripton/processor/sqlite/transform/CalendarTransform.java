package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.Calendar;
import java.util.Date;

import com.abubusoft.kripton.common.DateUtil;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a java.util.Calendar object
 * 
 * @author bulldog
 *
 */
public class CalendarTransform  extends AbstractCompileTimeTransform {
	 
	public Calendar read(String value) throws Exception {
		Date date = DateUtil.read(value);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public String write(Calendar value) throws Exception {
		Date date = value.getTime();
		String text = DateUtil.write(date);
		return text;
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateDefaultValue(Builder methodBuilder)
	{
		methodBuilder.addCode("null");
	}
	
	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(beanClass, property, "null"), beanName);
	}
	
	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getString($L)", cursorName, indexName);		
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "TEXT";
	}

}
