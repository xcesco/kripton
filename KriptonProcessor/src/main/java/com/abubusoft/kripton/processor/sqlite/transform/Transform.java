package com.abubusoft.kripton.processor.sqlite.transform;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;


/**
 * 
 * Class implementing this interface can be used to generate code to read and write the property
 * 
 * @author xcesco
 *
 */
public interface Transform {

	/**
	 * Generate code to put into cursor, the bean property value
	 */
	public void generateReadProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName);
	
	/**
	 * Generate a string representing code to write property
	 * 
	 * @param property property to write
	 * @return generated code to write property
	 */
	public String generateWriteProperty(ModelProperty property);

	/**
	 * Generate code to set property  to null value or default value
	 * @param methodBuilder
	 * @param property
	 * @param beanName
	 * @param cursorName
	 * @param indexName
	 */
	public void generateResetProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName);

	/**
	 * Generate code to define column 
	 * 
	 * @param property
	 */
	public String generateColumnType(ModelProperty property);
	
	
}
