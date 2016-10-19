/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 *
 *
 * @since 17/mag/2016
 */
public class SelectBeanHelper implements SelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generate(Elements elementUtils, PropertyList fieldList, MethodSpec.Builder methodBuilder, boolean mapFields,  SQLiteModelMethod method, TypeMirror returnType) {
		SQLDaoDefinition daoDefinition=method.getParent();
		SQLEntity entity=daoDefinition.getEntity();
		
		List<SQLProperty> fields = fieldList.value1;

		//TypeName collectionClass;
		TypeName entityClass = typeName(entity.getElement());
		
		methodBuilder.addCode("\n");		
		methodBuilder.addCode("$T resultBean=null;\n", entityClass);
		methodBuilder.addCode("\n");
						
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		{
			int i = 0;
			for (ModelProperty item : fields) {
				methodBuilder.addCode("int index" + (i++) + "=");
				methodBuilder.addCode("cursor.getColumnIndex($S)", SQLUtility.getColumnName(item));
				methodBuilder.addCode(";\n");
			}
		}
		methodBuilder.addCode("\n");

		methodBuilder.addCode("resultBean=new $T();\n\n", entityClass);

		// generate mapping
		int i = 0;
		for (SQLProperty item : fields) {
			
			if (item.isNullable()) {
				methodBuilder.addCode("if (!cursor.isNull(index$L)) { ", i);
			}
			Transformer.cursor2Java(methodBuilder, entityClass, item, "resultBean", "cursor", "index" + i + "");
			methodBuilder.addCode(";");
			if (item.isNullable()) {
				methodBuilder.addCode(" }");
			}
			methodBuilder.addCode("\n");

			i++;
		}
		methodBuilder.addCode("\n");

		methodBuilder.endControlFlow();
		methodBuilder.addCode("cursor.close();\n");

		methodBuilder.addCode("\n");
		methodBuilder.addCode("return resultBean;\n");
	}


}
