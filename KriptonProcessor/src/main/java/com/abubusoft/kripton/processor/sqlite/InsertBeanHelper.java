package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SQLiteInsertBuilder.InsertCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class InsertBeanHelper implements InsertCodeGenerator {

	@Override
	public String generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition=method.getParent();
		SQLEntity entity=daoDefinition.getEntity();
		String sqlInsert;

		List<SQLProperty> listUsedProperty = CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, daoDefinition, method, BindInsert.class, methodBuilder, null);
		CodeBuilderUtility.generateContentValuesFromEntity(elementUtils, daoDefinition, method, BindInsert.class, methodBuilder, null);

		ModelProperty primaryKey = entity.getPrimaryKey();

		//methodBuilder.addCode("\n");
		
		// generate javadoc and query
		sqlInsert = generateJavaDoc(methodBuilder, method, returnType, listUsedProperty, primaryKey);

		if (daoDefinition.isLogEnabled()) {
			methodBuilder.addCode("// log\n");
			methodBuilder.addCode("$T.info($T.formatSQL(\"SQL: $L\"));\n", Logger.class, StringUtil.class, sqlInsert);
		}

		methodBuilder.addCode("long result = database().insert($S, null, contentValues);\n", daoDefinition.getEntity().getTableName());

		if (primaryKey != null) {
			if (primaryKey.isPublicField()) {
				methodBuilder.addCode("$L.$L=result;\n", method.getParameters().get(0).value0, primaryKey.getName());
			} else {
				methodBuilder.addCode("$L.$L(result);\n", method.getParameters().get(0).value0, PropertyUtility.setter(typeName(entity.getElement()), primaryKey));
			}
		}

		// define return value
		if (returnType == TypeName.VOID) {

		} else if (TypeUtility.isTypeIncludedIn(returnType, String.class)) {
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return String.valueOf(result);\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return result!=-1;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return result;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return (int)result;\n");
		} else {
			// more than one listener found
			throw (new InvalidMethodSignException(method, "invalid return type"));
		}

		return sqlInsert;
	}

	/**
	 * @param methodBuilder
	 * @param method
	 * @param returnType
	 * @param listUsedProperty
	 * @param primaryKey
	 * @return
	 * 		string to use in log
	 */
	public String generateJavaDoc(MethodSpec.Builder methodBuilder, SQLiteModelMethod method, TypeName returnType, List<SQLProperty> listUsedProperty, ModelProperty primaryKey) {
		SQLDaoDefinition daoDefinition=method.getParent();
		
		String sqlInsert;
		// generate javadoc and result
		{
			String beanNameParameter = method.getParameters().get(0).value0;
			StringBuilder bufferName = new StringBuilder();
			StringBuilder bufferValue = new StringBuilder();
			StringBuilder bufferQuestion = new StringBuilder();
			String separator = "";
			for (SQLProperty property : listUsedProperty) {
				bufferName.append(separator + daoDefinition.getColumnNameConverter().convert(property.getName()));
				bufferValue.append(separator + "${" + beanNameParameter + "." + property.getName() + "}");
				bufferQuestion.append(separator + "'\"+StringUtil.checkSize(contentValues.get(\"" + daoDefinition.getColumnNameConverter().convert(property.getName()) + "\"))+\"'");
				separator = ", ";
			}

			methodBuilder.addJavadoc("<p>Insert query:</p>\n");
			methodBuilder.addJavadoc("<pre>INSERT INTO $L ($L) VALUES ($L)</pre>\n", daoDefinition.getEntity().getTableName(), bufferName.toString(), bufferValue.toString());
			methodBuilder.addJavadoc("<p><code>$L.$L</code> is automatically updated because it is the primary key</p>\n", beanNameParameter, primaryKey.getName());
			methodBuilder.addJavadoc("\n");						

			// generate sql query
			sqlInsert = String.format("INSERT INTO %s (%s) VALUES (%s)", daoDefinition.getEntity().getTableName(), bufferName.toString(), bufferQuestion.toString());

			// update bean have only one parameter: the bean to update
			for (Pair<String, TypeMirror> param : method.getParameters()) {
				methodBuilder.addJavadoc("@param $L", param.value0);
				methodBuilder.addJavadoc("\n\tused as updated field and in where condition\n");
			}

			if (returnType == TypeName.VOID) {
			} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
				methodBuilder.addJavadoc("@return true if record is inserted");
				methodBuilder.addJavadoc("\n");
			} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class)) {
				methodBuilder.addJavadoc("@return id of inserted record");
				methodBuilder.addJavadoc("\n");
			} else if (TypeUtility.isTypeIncludedIn(returnType, Integer.TYPE, Integer.class)) {
				methodBuilder.addJavadoc("@return id of inserted record");
				methodBuilder.addJavadoc("\n");
			}

		}
		return sqlInsert;
	}

}
