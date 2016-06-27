package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.isNullable;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import android.content.ContentValues;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.SQLiteModifyBuilder.ModifyCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class ModifyRawHelper implements ModifyCodeGenerator {

	public void generate(Elements elementUtils, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		// separate params used for update bean and params used in whereCondition
		// analyze whereCondition
		String whereCondition = null;

		if (updateMode) {
			whereCondition = method.getAnnotation(BindUpdate.class).getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);
		} else {
			whereCondition = method.getAnnotation(BindDelete.class).getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);
		}

		Pair<String, List<Pair<String, TypeMirror>>> where = SQLUtility.extractParametersFromString(whereCondition, method, daoDefinition.getColumnNameConverter(), entity);

		// defines which parameter is used like update field and which is used in where condition.
		List<Pair<String, TypeMirror>> methodParams = method.getParameters();
		List<Pair<String, TypeMirror>> updateableParams = new ArrayList<Pair<String, TypeMirror>>();
		List<Pair<String, TypeMirror>> whereParams = new ArrayList<Pair<String, TypeMirror>>();

		for (Pair<String, TypeMirror> param : methodParams) {
			if (where.value1.contains(param)) {
				whereParams.add(param);
			} else {
				updateableParams.add(param);
			}
		}

		if (updateMode) {
			// clear contentValues
			methodBuilder.addCode("$T contentValues=contentValues();\n", ContentValues.class);
			methodBuilder.addCode("contentValues.clear();\n\n");
			
			for (Pair<String, TypeMirror> item : updateableParams) {
				ModelProperty property = entity.get(item.value0);
				if (property == null)
					throw (new PropertyNotFoundException(method, item.value0));

				if (TypeUtility.isNullable(method, item, property)) {
					methodBuilder.beginControlFlow("if ($L!=null)", item.value0);
				}
				methodBuilder.addCode("contentValues.put($S, $L);\n", daoDefinition.getColumnNameConverter().convert(property.getName()), item.value0);
				if (TypeUtility.isNullable(method, item, property)) {
					methodBuilder.nextControlFlow("else");
					methodBuilder.addCode("contentValues.putNull($S);\n", daoDefinition.getColumnNameConverter().convert(property.getName()));
					methodBuilder.endControlFlow();
				}
			}

			methodBuilder.addCode("\n");
		} else {
			if (updateableParams.size() > 0) {
				String separator = "";
				StringBuilder buffer = new StringBuilder();
				for (Pair<String, TypeMirror> item : updateableParams) {
					buffer.append(separator + item.value0);
					separator = ", ";
				}
				// in DELETE can not be updated fields
				if (updateableParams.size() > 1) {
					throw (new InvalidMethodSignException(method, " parameters " + buffer.toString() + " are not used in where conditions"));
				} else {
					throw (new InvalidMethodSignException(method, " parameter " + buffer.toString() + " is not used in where conditions"));
				}
			}
		}

		// build where condition
		generateWhereCondition(methodBuilder, where);

		methodBuilder.addCode("\n");
		methodBuilder.addCode("\n");

		// generate javadoc
		String sqlModify = generateJavaDoc(daoDefinition, methodBuilder, updateMode, whereCondition, where, methodParams, updateableParams);

		if (updateMode) {
			if (daoDefinition.isLogEnabled()) {
				methodBuilder.addCode("$T.info($T.formatSQL(\"$L\"), (Object[])whereConditions);\n", Logger.class, StringUtil.class, sqlModify);
			}
			methodBuilder.addCode("int result = database().update($S, contentValues, $S, whereConditions);\n", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()), where.value0);
		} else {
			if (daoDefinition.isLogEnabled()) {
				methodBuilder.addCode("$T.info($T.formatSQL(\"$L\"), (Object[])whereConditions);\n", Logger.class, StringUtil.class, sqlModify);
			}
			methodBuilder.addCode("int result = database().delete($S, $S, whereConditions);\n", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()), where.value0);
		}

		// define return value
		if (returnType == TypeName.VOID) {

		} else if (isIn(returnType, Boolean.TYPE, Boolean.class)) {
			if (updateMode) {
				methodBuilder.addJavadoc("\n@return true if record is updated");
			} else {
				methodBuilder.addJavadoc("\n@return true if record is deleted");
			}
			methodBuilder.addCode("return result!=0;\n");
		} else if (isIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
			if (updateMode) {
				methodBuilder.addJavadoc("\n@return number of updated records\n");
			} else {
				methodBuilder.addJavadoc("\n@return number of deleted records\n");
			}
			methodBuilder.addCode("return result;\n");
		} else {
			// more than one listener found
			throw (new InvalidMethodSignException(method, "invalid return type"));
		}

	}

	/**
	 * @param daoDefinition
	 * @param methodBuilder
	 * @param updateMode
	 * @param whereCondition
	 * @param where
	 * @param methodParams
	 * @param updateableParams
	 */
	public String generateJavaDoc(SQLDaoDefinition daoDefinition, MethodSpec.Builder methodBuilder, boolean updateMode, String whereCondition, Pair<String, List<Pair<String, TypeMirror>>> where, List<Pair<String, TypeMirror>> methodParams,
			List<Pair<String, TypeMirror>> updateableParams) {
		String sqlResult;
		StringBuilder buffer = new StringBuilder();
		StringBuilder bufferQuestion = new StringBuilder();

		String separator = "";
		for (Pair<String, TypeMirror> param : updateableParams) {
			buffer.append(separator + param.value0 + "=${" + param.value0 + "}");
			bufferQuestion.append(separator + param.value0 + "='\"+StringUtil.checkSize(contentValues.get(\"" + daoDefinition.getColumnNameConverter().convert(param.value0) + "\"))+\"'");

			separator = ", ";
		}

		// used for logging
		String whereForLogging = SQLUtility.replaceParametersWithQuestion(whereCondition, "%s");

		if (updateMode) {
			// generate sql query
			sqlResult = String.format("UPDATE %s SET %s WHERE %s", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()), bufferQuestion.toString(), whereForLogging);

			methodBuilder.addJavadoc("<p>Update query:</p>\n");
			methodBuilder.addJavadoc("<pre>UPDATE $L SET $L WHERE $L</pre>\n", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()), buffer.toString(), whereCondition);
		} else {
			// generate sql query
			sqlResult = String.format("DELETE %s WHERE %s", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()), whereForLogging);

			methodBuilder.addJavadoc("<p>Delete query:</p>\n");
			methodBuilder.addJavadoc("<pre>DELETE $L WHERE $L</pre>\n", daoDefinition.getClassNameConverter().convert(daoDefinition.getEntitySimplyClassName()), whereCondition);
		}

		if (methodParams.size() > 0) {
			methodBuilder.addJavadoc("\n");
			for (Pair<String, TypeMirror> param : methodParams) {
				methodBuilder.addJavadoc("@param $L", param.value0);
				if (where.value1.contains(param)) {
					methodBuilder.addJavadoc("\n\tused in where condition\n");
				} else {
					methodBuilder.addJavadoc("\n\tused as updated field\n");
				}
			}
		}

		return sqlResult;
	}

	/**
	 * @param methodBuilder
	 * @param where
	 */
	public void generateWhereCondition(MethodSpec.Builder methodBuilder, Pair<String, List<Pair<String, TypeMirror>>> where) {
		boolean nullable;

		methodBuilder.addCode("String[] whereConditions={");
		String separator = "";
		for (Pair<String, TypeMirror> item : where.value1) {
			methodBuilder.addCode(separator);

			nullable = isNullable(typeName(item.value1));

			if (nullable) {
				methodBuilder.addCode("($L==null?null:", item.value0);
			}
			methodBuilder.addCode("String.valueOf(");
			Transformer.java2ContentValues(methodBuilder, item.value1, item.value0);
			methodBuilder.addCode(")");
			if (nullable) {
				methodBuilder.addCode(")");
			}

			separator = ", ";
		}
		methodBuilder.addCode("};");
	}

	static boolean isIn(TypeName value, Class<?>... classes) {
		for (Class<?> item : classes) {
			if (value.toString().equals(TypeName.get(item).toString())) {
				return true;
			}
		}

		return false;
	}

}
