/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.List;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.MethodUtility;
import com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 *
 *
 * @since 17/mag/2016
 */
public class SelectBeanListenerHelper implements SelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generate(Elements elementUtils, Pair<String, List<SQLProperty>> fieldList, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition=method.getParent();
		SQLEntity entity=daoDefinition.getEntity();
		
		LiteralType listenerType = LiteralType.of(ReadBeanListener.class, entity.getElement());
		List<SQLProperty> fields = fieldList.value1;
		TypeName entityClass = typeName(entity.getElement());

		int counter = MethodUtility.countParameterOfType(method, listenerType);
		if (counter == 0) {
			// non listener found
			throw (new InvalidMethodSignException(daoDefinition, method, "there is no parameter of type \"ReadCursorListener\""));
		}
		if (counter > 1) {
			// more than one listener found
			throw (new InvalidMethodSignException(daoDefinition, method, "there are more than one parameter of type \"ReadCursorListener\""));
		}

		String listenerName = MethodUtility.getNameParameterOfType(method, listenerType);

		methodBuilder.addCode("$T resultBean=new $T();\n", entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("try");
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
		methodBuilder.addCode("int rowCount=cursor.getCount();\n");

		methodBuilder.beginControlFlow("do\n");

		// reset mapping
		methodBuilder.addCode("// reset mapping (only for nullable property)\n");
		{
			int i = 0;
			for (SQLProperty item : entity.getCollection()) {
				if (item.isNullable()) {
					Transformer.resetBean(methodBuilder, entityClass, "resultBean", item,  "cursor", "index" + i + "");
					methodBuilder.addCode(";");
					methodBuilder.addCode("\n");
				} else {
					methodBuilder.addCode("// "+item.getName()+" does not need reset\n");
				}
				i++;
			}
		}
		methodBuilder.addCode("\n");

		// generate mapping
		methodBuilder.addCode("// generate mapping\n");
		{
			int i = 0;
			for (SQLProperty item : fields) {

				if (item.isNullable()) {
					methodBuilder.addCode("if (!cursor.isNull(index$L)) { ", i);
				}
				Transformer.cursor2Java(methodBuilder, typeName(entity.getElement()), item, "resultBean", "cursor", "index" + i + "");
				methodBuilder.addCode(";");
				if (item.isNullable()) {
					methodBuilder.addCode(" }");
				}
				methodBuilder.addCode("\n");

				i++;
			}
		}
		methodBuilder.addCode("\n");

		methodBuilder.addCode("$L.onRead(resultBean, cursor.getPosition(), rowCount);\n", listenerName);
		methodBuilder.endControlFlow("while (cursor.moveToNext())");

		methodBuilder.endControlFlow();
		//methodBuilder.nextControlFlow("catch(Throwable e)");
		//methodBuilder.addCode("throw (e);\n");
		methodBuilder.nextControlFlow("finally");
		methodBuilder.beginControlFlow("if (cursor!=null)\n");
		methodBuilder.addCode("cursor.close();\n");
		methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();
	}

}
