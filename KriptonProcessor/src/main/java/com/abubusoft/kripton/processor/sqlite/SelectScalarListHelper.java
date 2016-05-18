/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import java.util.List;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 *
 *
 * @since 17/mag/2016
 */
public class SelectScalarListHelper implements SelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generate(Elements elementUtils, SQLDaoDefinition daoDefinition, SQLEntity entity,Pair<String, List<SQLProperty>> fieldList,  MethodSpec.Builder methodBuilder, boolean mapFields,  SQLiteModelMethod method,TypeName returnType) {
		// TODO Auto-generated method stub

	}


}
