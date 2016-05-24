package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public class SQLUtility {
	private static final Pattern PARAMETER = Pattern.compile("\\$\\{\\s*([\\w]*)\\s*\\}");

	private static final Pattern WORD = Pattern.compile("([_a-zA-Z]\\w*)");

	/**
	 * Extract from value string every placeholder ${}, replace it with ? and then convert every field name with column name. The result is a pair: the first value is the elaborated string. The second is the list of parameters associated to
	 * ?. This second parameter is the list of parameters and replaced with ?.
	 * 
	 * @param value
	 * @param method
	 * @param columnNameConverter
	 * @param entity
	 * @return Pair<String, List<String>>
	 */
	public static Pair<String, List<Pair<String, TypeMirror>>> extractParametersFromString(String value, SQLiteModelMethod method, Converter<String, String> columnNameConverter, SQLEntity entity) {
		String whereStatement = value;
		Pair<String, List< Pair<String, TypeMirror>>> result = new Pair<String, List< Pair<String, TypeMirror>>>();
		result.value1 = new ArrayList< Pair<String, TypeMirror>>();

		// replace placeholder ${ } with ?
		{
			Matcher matcher = PARAMETER.matcher(whereStatement);

			String paramName;
			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(buffer, "?");
				paramName=matcher.group(1);				
				result.value1.add(new Pair<String, TypeMirror>(paramName, method.findParameter(paramName)));
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
	 * Convert java property name in sql column name.
	 */
	static Converter<String, String> field2ColumnConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

	/**
	 * Convert java property name in sql column name.
	 */
	static Converter<String, String> field2ColumnNameFromTableConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

	/**
	 * Obtain column name for property
	 * 
	 * @param property
	 * @return column name
	 */
	public static String getColumnName(ModelProperty property) {
		return getColumnName(property.getName());
	}

	/**
	 * Obtain column name for property
	 * 
	 * @param property
	 * @return column name
	 */
	public static String getColumnName(String propertyName) {
		return field2ColumnConverter.convert(propertyName);
	}

	public static String nameFromTable(ModelClass entity, ModelProperty property) {
		return entity.getSimpleName() + "Table." + field2ColumnNameFromTableConverter.convert(property.getName());
	}

}
