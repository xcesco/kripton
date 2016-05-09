package com.abubusoft.kripton.processor.core.reflect;

import java.util.List;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

public class JavaDocUtility {

	public static void generateJavaDocForSelect(MethodSpec.Builder methodBuilder, String sql, List<String> sqlParams, SQLiteModelMethod method, ModelAnnotation annotation, String fieldStatement)
	{
		methodBuilder.addJavadoc("<p>Projected column are:</p>\n\n");
		methodBuilder.addJavadoc("<pre>[$L]</pre>\n\n",fieldStatement);
		
		methodBuilder.addJavadoc("<p>Query used with this $L is:</p>\n\n", annotation.getSimpleName());
		methodBuilder.addJavadoc("<pre>$L</pre>\n\n",sql);
		
		methodBuilder.addJavadoc("<p>Its parameters are:</p>\n\n");
		methodBuilder.addJavadoc("<pre>[");
		String separator="";
		for (String param: sqlParams)
		{
			methodBuilder.addJavadoc(separator+"$L",param);
			separator=", ";
		}
		methodBuilder.addJavadoc("]</pre>\n\n");
		
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			
			methodBuilder.addJavadoc("@param $L\n", parameterSpec.name);
						
		}
	}
}
