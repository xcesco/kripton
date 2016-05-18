/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.reflect.MethodUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

/**
 * @author xcesco
 *
 *
 * @since 17/mag/2016
 */
public class SelectCursorListenerHelper implements SelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generate(Elements elementUtils, SQLDaoDefinition daoDefinition, SQLEntity entity,Pair<String, List<SQLProperty>> fieldList,  MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {		
		int counter=MethodUtility.countParameterOfType(method, typeName(ReadCursorListener.class));
		if (counter==0)
		{
			// non listener found
			throw(new InvalidMethodSignException(daoDefinition, method, "there is no parameter of type \"ReadCursorListener\""));
		}
		if (counter>1)
		{
			// more than one listener found
			throw(new InvalidMethodSignException(daoDefinition, method, "there are more than one parameter of type \"ReadCursorListener\""));
		}

	}


}
