/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import java.util.List;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transform;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * Manage query with only one value
 * 
 * @author xcesco
 *
 *
 * @since 17/mag/2016
 */
public class SelectScalarHelper implements SelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generate(Elements elementUtils, Pair<String, List<SQLProperty>> fieldList, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition=method.getParent();
		
		if (TypeUtility.isTypePrimitive(returnType) || TypeUtility.isTypeWrappedPrimitive(returnType) || TypeUtility.isTypeIncludedIn(returnType, String.class) || TypeUtility.isByteArray(returnType)) {

		} else {
			// error return type
			throw (new InvalidMethodSignException(daoDefinition, method, "Invalid return type"));
		}

		if (fieldList.value1.size() == 0) {
			// no projection
			throw (new InvalidMethodSignException(daoDefinition, method, "No column was selected"));
		} else if (fieldList.value1.size() > 1) {
			// too many values
			throw (new InvalidMethodSignException(daoDefinition, method, "Only one column can be defined for this kind of method"));
		}
		
		Transform t = Transformer.lookup(returnType);

		methodBuilder.addCode("$T result=",returnType);
		t.generateDefaultValue(methodBuilder);
		methodBuilder.addCode(";\n");
		
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("try");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("do\n");
		
		//methodBuilder.addCode("if (cursor.getString(0);\n");
		if (TypeUtility.isNullable(returnType)) {
			methodBuilder.addCode("if (cursor.isNull(0)) { return null; }\n");
		} else  {
			methodBuilder.addCode("if (cursor.isNull(0)) { return 0; }\n");
		}
		methodBuilder.addCode("result=");
		t.generateRead(methodBuilder, "cursor", "0");
		methodBuilder.addCode(";\n");

		methodBuilder.endControlFlow("while (cursor.moveToNext())");

		methodBuilder.endControlFlow();
		methodBuilder.nextControlFlow("finally");
		methodBuilder.beginControlFlow("if (cursor!=null)\n");
		methodBuilder.addCode("cursor.close();\n");
		methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();
		
		methodBuilder.addCode("return result;\n");
	}

}
