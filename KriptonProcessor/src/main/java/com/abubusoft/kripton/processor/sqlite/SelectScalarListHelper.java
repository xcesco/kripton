/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transform;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
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
	public void generate(Elements elementUtils, PropertyList fieldList, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeMirror returnType) {
		TypeName returnTypeName=typeName(returnType);
		
		// return type is already checked
		if (fieldList.value1.size() == 0) {
			// no projection
			throw (new InvalidMethodSignException(method, "no column was selected"));
		} else if (fieldList.value1.size() > 1) {
			// too many values
			throw (new InvalidMethodSignException(method, "only one column can be defined for this kind of method"));
		}
		
		ParameterizedTypeName returnListName = (ParameterizedTypeName) returnTypeName;

		TypeName collectionClass;		
		ClassName listClazzName = returnListName.rawType;

		if (TypeUtility.isTypeIncludedIn(listClazzName.toString(), List.class, Collection.class, Iterable.class)) {
			// there is an interface as result
			collectionClass = typeName(LinkedList.class);
		} else {
			collectionClass = listClazzName;
		}
		TypeName elementName = returnListName.typeArguments.get(0);
		
		methodBuilder.addCode("\n");
		methodBuilder.addCode("$T<$T> resultList=new $T<$T>();\n", collectionClass, elementName, collectionClass, elementName);		
		methodBuilder.addCode("\n");
	
		//elementName.
		Transform t = Transformer.lookup(returnType);

		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("try");
			methodBuilder.beginControlFlow("if (cursor.moveToFirst())");
				methodBuilder.addCode("\n");
				methodBuilder.beginControlFlow("do\n");
					methodBuilder.beginControlFlow("if (!cursor.isNull(0))");
						methodBuilder.addCode("resultList.add(");
						t.generateRead(methodBuilder, "cursor", "0");
						methodBuilder.addCode(");\n");
					methodBuilder.nextControlFlow("else");
						methodBuilder.addCode("resultList.add(null);\n");		
					methodBuilder.endControlFlow();
				methodBuilder.endControlFlow("while (cursor.moveToNext())");
			methodBuilder.endControlFlow();
		methodBuilder.nextControlFlow("finally");
			methodBuilder.beginControlFlow("if (cursor!=null)\n");
				methodBuilder.addCode("cursor.close();\n");
			methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();

		methodBuilder.addCode("return resultList;\n");

	}

}
