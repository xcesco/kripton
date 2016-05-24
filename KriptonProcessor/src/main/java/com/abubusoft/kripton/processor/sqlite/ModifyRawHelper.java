package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.SQLiteModifyBuilder.ModifyCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class ModifyRawHelper implements ModifyCodeGenerator {

	public void generate(Elements elementUtils, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, SQLEntity entity, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		// separate params used for update bean and params used in whereCondition
		// analyze whereCondition
		String whereCondition=null;

		if (updateMode) {
			whereCondition = method.getAnnotation(BindUpdate.class).getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);
		} else {
			whereCondition = method.getAnnotation(BindDelete.class).getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);
		}

		Pair<String, List<Pair<String, TypeMirror>>> where = SQLUtility.extractParametersFromString(whereCondition, method, model.columnNameConverter, entity);

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
			// put params in contentValues
			methodBuilder.addCode("contentValues.clear();\n\n");
			for (Pair<String, TypeMirror> item : updateableParams) {
				ModelProperty a = entity.get(item.value0);
				if (a == null)
					throw (new PropertyNotFoundException(daoDefinition, method, item.value0));

				methodBuilder.addCode("contentValues.put($S, $L);\n", model.columnNameConverter.convert(a.getName()), item.value0);
			}
			
			methodBuilder.addCode("\n");
		} else {
			if (updateableParams.size()>0)
			{
				String separator="";
				StringBuilder buffer=new StringBuilder();
				for (Pair<String, TypeMirror> item: updateableParams)
				{
					buffer.append(separator+item.value0);
					separator=", ";
				}
				// in DELETE can not be updated fields
				if (updateableParams.size()>1)
				{
					throw(new InvalidMethodSignException(daoDefinition, method, " parameters "+buffer.toString()+" are not used in where conditions"));
				} else {
					throw(new InvalidMethodSignException(daoDefinition, method, " parameter "+buffer.toString()+" is not used in where conditions"));
				}
			}
		}
		

		// build where condition
		{
			methodBuilder.addCode("String[] whereConditions={");
			String separator = "";
			for (Pair<String, TypeMirror> item : where.value1) {
				methodBuilder.addCode(separator);

				methodBuilder.addCode("String.valueOf(");
				Transformer.java2ContentValues(methodBuilder, item.value1, item.value0);
				methodBuilder.addCode(")");

				separator = ", ";
			}
			methodBuilder.addCode("};");
		}

		methodBuilder.addCode("\n");
		methodBuilder.addCode("\n");

		if (updateMode) {
			methodBuilder.addCode("int result = database.update($S, contentValues, $S, whereConditions);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), where.value0);
		} else {
			methodBuilder.addCode("int result = database.delete($S, $S, whereConditions);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), where.value0);
		}

		// generate javadoc
		{
			StringBuilder buffer = new StringBuilder();
			String separator = "";
			for (Pair<String, TypeMirror> param : updateableParams) {
				buffer.append(separator + param.value0 + "=${" + param.value0 + "}");
				separator = ", ";
			}

			if (updateMode) {
				methodBuilder.addJavadoc("<p>Update query:</p>\n");
				methodBuilder.addJavadoc("<pre>UPDATE $L SET $L WHERE $L</pre>\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), buffer.toString(), whereCondition);
			} else {
				methodBuilder.addJavadoc("<p>Delete query:</p>\n");
				methodBuilder.addJavadoc("<pre>DELETE $L WHERE $L</pre>\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), whereCondition);
			}
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
			throw (new InvalidMethodSignException(daoDefinition, method, "invalid return type"));
		}

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
