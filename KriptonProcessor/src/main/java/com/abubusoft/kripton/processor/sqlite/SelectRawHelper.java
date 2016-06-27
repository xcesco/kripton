/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 *
 *
 * @since 17/mag/2016
 */
public class SelectRawHelper implements SelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generate(Elements elementUtils, PropertyList fieldList,  MethodSpec.Builder methodBuilder, boolean mapFields,  SQLiteModelMethod method,TypeName returnType) {		
		methodBuilder.addCode("return cursor;\n");
	}


}
