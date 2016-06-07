package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.Logger;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.SQLiteInsertBuilder.InsertCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class InsertRawHelper implements InsertCodeGenerator {

	@Override
	public String generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition=method.getParent();
		SQLEntity entity=daoDefinition.getEntity();
		
		String sqlInsert;
		boolean nullable;
		
		// generate javadoc
		sqlInsert = generateJavaDoc(methodBuilder, method, returnType);
		
		methodBuilder.addCode("contentValues.clear();\n\n");
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			ModelProperty property = entity.get(item.value0);
			if (property == null)
				throw (new PropertyNotFoundException(daoDefinition, method, item.value0));
			
			nullable=TypeUtility.isNullable(property);

			if (nullable)
			{
				methodBuilder.beginControlFlow("if ($L!=null)", item.value0);
			}
			methodBuilder.addCode("contentValues.put($S, ", daoDefinition.getColumnNameConverter().convert(property.getName()));
			Transformer.java2ContentValues(methodBuilder, item.value1, item.value0);
			methodBuilder.addCode(");\n");
			if (nullable)
			{
				methodBuilder.nextControlFlow("else");
				methodBuilder.addCode("contentValues.putNull($S);\n", daoDefinition.getColumnNameConverter().convert(item.value0));				
				methodBuilder.endControlFlow();				
			}
			methodBuilder.addCode("\n");
		}

		//methodBuilder.addCode("\n");
		
		if (daoDefinition.isLogEnabled())
		{
			methodBuilder.addCode("// log\n");
			methodBuilder.addCode("$T.info(\"SQL: $L\");\n", Logger.class, sqlInsert);
		}

		// define return value
		if (returnType == TypeName.VOID) {
			methodBuilder.addCode("database.insert($S, null, contentValues);\n", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()));
		} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addCode("long result = database.insert($S, null, contentValues);\n", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()));
			methodBuilder.addCode("return result!=-1;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
			methodBuilder.addCode("long result = database.insert($S, null, contentValues);\n", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()));
			methodBuilder.addCode("return result;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
			methodBuilder.addCode("int result = (int)database.insert($S, null, contentValues);\n", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()));
			methodBuilder.addCode("return result;\n");
		} else {
			// more than one listener found
			throw (new InvalidMethodSignException(daoDefinition, method, "invalid return type"));
		}
		
		return sqlInsert;
	}

	/**
	 * @param methodBuilder
	 * @param method
	 * @param returnType
	 * @return
	 * 		string sql
	 */
	public String generateJavaDoc(MethodSpec.Builder methodBuilder, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition=method.getParent();
		
		String sqlInsert;
		{
			StringBuilder bufferName = new StringBuilder();
			StringBuilder bufferValue = new StringBuilder();
			StringBuilder bufferQuestion = new StringBuilder();
			
			String separator = "";
			for (Pair<String, TypeMirror> item : method.getParameters()) {
				bufferName.append(separator + daoDefinition.getColumnNameConverter().convert(item.value0));
				bufferValue.append(separator + "${" + item.value0 + "}");
				bufferQuestion.append(separator + "'\"+checkSize(contentValues.get(\""+daoDefinition.getColumnNameConverter().convert(item.value0)+"\"))+\"'");
				separator = ", ";
			}

			methodBuilder.addJavadoc("<p>Insert query:</p>\n");
			methodBuilder.addJavadoc("<pre>INSERT INTO $L ($L) VALUES ($L)</pre>\n", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()), bufferName.toString(), bufferValue.toString());
			methodBuilder.addJavadoc("\n");
			
			// generate sql query
			sqlInsert=String.format("INSERT INTO %s (%s) VALUES (%s)",daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()), bufferName.toString(),bufferQuestion.toString());

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
		return sqlInsert;
	}
	
	
	
}
