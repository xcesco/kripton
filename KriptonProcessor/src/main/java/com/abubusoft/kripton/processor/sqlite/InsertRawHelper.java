package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.SQLiteInsertBuilder.InsertCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class InsertRawHelper implements InsertCodeGenerator {

	@Override
	public void generate(Elements elementUtils, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, SQLEntity entity, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {
		// code
		methodBuilder.addCode("contentValues.clear();\n\n");
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			ModelProperty a = entity.get(item.value0);
			if (a == null)
				throw (new PropertyNotFoundException(daoDefinition, method, item.value0));

			methodBuilder.addCode("contentValues.put($S, ", model.columnNameConverter.convert(a.getName()));
			Transformer.java2ContentValues(methodBuilder, item.value1, item.value0);
			methodBuilder.addCode(");\n");
		}

		methodBuilder.addCode("\n");

		// define return value
		if (returnType == TypeName.VOID) {
			methodBuilder.addCode("database.insert($S, null, contentValues);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()));
		} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addCode("long result = database.insert($S, null, contentValues);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()));
			methodBuilder.addCode("return result!=-1;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
			methodBuilder.addCode("long result = database.insert($S, null, contentValues);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()));
			methodBuilder.addCode("return result;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
			methodBuilder.addCode("int result = (int)database.insert($S, null, contentValues);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()));
			methodBuilder.addCode("return result;\n");
		} else {
			// more than one listener found
			throw (new InvalidMethodSignException(daoDefinition, method, "invalid return type"));
		}

		// generate javadoc
		{
			StringBuilder bufferName = new StringBuilder();
			StringBuilder bufferValue = new StringBuilder();
			String separator = "";
			for (Pair<String, TypeMirror> item : method.getParameters()) {
				bufferName.append(separator + model.columnNameConverter.convert(item.value0));
				bufferValue.append(separator + "${" + item.value0 + "}");
				separator = ", ";
			}

			methodBuilder.addJavadoc("<p>Insert query:</p>\n");
			methodBuilder.addJavadoc("<pre>INSERT INTO $L ($L) VALUES ($L)</pre>\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), bufferName.toString(), bufferValue.toString());
			methodBuilder.addJavadoc("\n");

			// update bean have only one parameter: the bean to update
			for (Pair<String, TypeMirror> param : method.getParameters()) {
				methodBuilder.addJavadoc("@param $L", param.value0);
				methodBuilder.addJavadoc("\n\tused as updated field and in where condition\n");
			}

			if (returnType == TypeName.VOID) {
			} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
				methodBuilder.addJavadoc("@return true if record is inserted");
			} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
				methodBuilder.addJavadoc("@return id of inserted record");
			} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
				methodBuilder.addJavadoc("@return id of inserted record");
			} 
			methodBuilder.addJavadoc("\n");
		}

	}
	
	
	
}
