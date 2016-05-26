package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.SQLiteInsertBuilder.InsertCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class InsertBeanHelper implements InsertCodeGenerator {

	@Override
	public void generate(Elements elementUtils, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, SQLEntity entity, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {

		List<SQLProperty> listUsedProperty = CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, model, daoDefinition, method, BindInsert.class, methodBuilder, null);
		CodeBuilderUtility.generateContentValuesFromEntity(elementUtils, model, daoDefinition, method, BindInsert.class, methodBuilder, null);

		ModelProperty primaryKey = entity.getPrimaryKey();
 
		methodBuilder.addCode("\n");
		methodBuilder.addCode("long result = database.insert($S, null, contentValues);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()));

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
			throw (new InvalidMethodSignException(daoDefinition, method, "invalid return type"));
		}

		// generate javadoc
		{
			String beanNameParameter = method.getParameters().get(0).value0;
			StringBuilder bufferName = new StringBuilder();
			StringBuilder bufferValue = new StringBuilder();
			String separator = "";
			for (SQLProperty property : listUsedProperty) {
				bufferName.append(separator + model.columnNameConverter.convert(property.getName()));
				bufferValue.append(separator + "${" + beanNameParameter + "." + property.getName() + "}");
				separator = ", ";
			}

			methodBuilder.addJavadoc("<p>Insert query:</p>\n");
			methodBuilder.addJavadoc("<pre>INSERT INTO $L ($L) VALUES ($L)</pre>\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), bufferName.toString(),bufferValue.toString());
			methodBuilder.addJavadoc("<p><code>$L.$L</code> is automatically updated because it is the primary key</p>\n",beanNameParameter, primaryKey.getName());
			methodBuilder.addJavadoc("\n");

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
	}

}
