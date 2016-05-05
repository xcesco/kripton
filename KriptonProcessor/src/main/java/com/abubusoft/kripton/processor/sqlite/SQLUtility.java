package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.ModelEntity;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;

public class SQLUtility {
	private static final Pattern PARAMETER = Pattern.compile("\\$\\{\\s*([\\w]*)\\s*\\}");

	private static final Pattern WORD = Pattern.compile("([_a-zA-Z]\\w*)");

	/**
	 * Extract from value string every placeholder ${}, replace it with ? and then convert every field name with column name. The result is a pair: the first value is the elaborated string. The second is the list of parameters associated to
	 * ?. This second parameter is the list of parameters and replaced with ?.
	 * 
	 * @param value
	 * @param columnNameConverter
	 * @param entity
	 * @return Pair<String, List<String>>
	 */
	public static Pair<String, List<String>> extractParametersFromString(String value, Converter<String, String> columnNameConverter, SQLEntity entity) {
		String whereStatement = value;
		Pair<String, List<String>> result = new Pair<String, List<String>>();
		result.value1 = new ArrayList<String>();

		// replace placeholder ${ } with ?
		{
			Matcher matcher = PARAMETER.matcher(whereStatement);

			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(buffer, "?");
				result.value1.add(matcher.group(1));
			}
			matcher.appendTail(buffer);

			whereStatement = buffer.toString();
		}

		// replace fields
		{
			Matcher matcher = WORD.matcher(whereStatement);

			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				ModelProperty property = entity.findByName(matcher.group(1));
				if (property != null) {
					matcher.appendReplacement(buffer, columnNameConverter.convert(matcher.group(1)));
				}

			}
			matcher.appendTail(buffer);
			whereStatement = buffer.toString();
		}

		result.value0 = whereStatement;

		return result;
	}

	/**
	 * Check if typeName belong to classes set defined.
	 * 
	 * @param typeName
	 * @param classes
	 * @return true if typeName belong to classes set defined.
	 */
	public static boolean isIn(TypeName typeName, Class<?>... classes) {
		String value=typeName.toString();
		for (Class<?> item : classes) {
			if (value.equals(TypeName.get(item).toString())) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Check if typeMirror belong to classes set defined.
	 * 
	 * @param typeMirror
	 * @param classes
	 * @return true if typeMirror belong to classes set defined.
	 */
	public static boolean isIn(TypeMirror typeMirror, String ... classes) {
		TypeName value=TypeName.get(typeMirror);
		return isIn(value.toString(), classes);
	}
	
	/**
	 * Check if class that is rapresented by typeMirror has same name of className parameter.
	 * 
	 * @param typeMirror
	 * @param entity
	 * @return true if typeMirror is equals to className.
	 */
	public static boolean isEquals(TypeMirror typeMirror, ModelClass entity) {
		TypeName value=TypeName.get(typeMirror);
		return value.toString().equals(entity.getName());
	}
	
	public static boolean isIn(String value, String ... classes) {
		for (String item : classes) {
			if (value.equals(item)) {
				return true;
			}
		}

		return false;
	}
}
