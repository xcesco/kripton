/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.exceptions.MethodParameterNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.TypeName;

public class SqlUtility {
	private static final Pattern PARAMETER = Pattern.compile("\\$\\{\\s*([\\w\\.]*)\\s*\\}");

	private static final Pattern WORD = Pattern.compile("([_a-zA-Z]\\w*)");

	/**
	 * Extract from value string every placeholder ${}, replace it with ? and then convert every field typeName with column typeName. The result is a pair: the first value is the elaborated string. The second is the list of parameters associated to
	 * ?. This second parameter is the list of parameters and replaced with ?.
	 * 
	 * @param value
	 * @param method
	 * @param columnNameConverter
	 * @param entity
	 * @return Pair<String, List<String>>
	 */
	public static Pair<String, List<Pair<String, TypeName>>> extractParametersFromString(String value, SQLiteModelMethod method, SQLEntity entity) {
		String whereStatement = value;
		Pair<String, List< Pair<String, TypeName>>> result = new Pair<String, List< Pair<String, TypeName>>>();
		result.value1 = new ArrayList< Pair<String, TypeName>>();

		// replace placeholder ${ } with ?
		{
			Matcher matcher = PARAMETER.matcher(whereStatement);

			String paramName;
			StringBuffer buffer = new StringBuffer();
			TypeName paramType;
			while (matcher.find()) {
				matcher.appendReplacement(buffer, "?");
				paramName=matcher.group(1);				
				paramType=method.findParameterTypeByAliasOrName(paramName);
				
				if (paramType==null)
				{
					throw(new MethodParameterNotFoundException(method, paramName));
				}
				result.value1.add(new Pair<String, TypeName>(paramName, paramType));
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
					matcher.appendReplacement(buffer, entity.findByName(matcher.group(1)).columnName);
				}

			}
			matcher.appendTail(buffer);
			whereStatement = buffer.toString();
		}

		result.value0 = whereStatement;

		return result;
	}
	
	/**
	 * Extract from value string every placeholder ${}, replace it with ? and then convert every field typeName with column typeName. The result is a pair: the first value is the elaborated string. The second is the list of parameters associated to
	 * ?. This second parameter is the list of parameters and replaced with ?.
	 * 
	 * @param value
	 * @return statement with ? replaced
	 */
	public static String replaceParametersWithQuestion(String value, String replaceValue) {
		String whereStatement = value;
		Pair<String, List< Pair<String, TypeMirror>>> result = new Pair<String, List< Pair<String, TypeMirror>>>();
		result.value1 = new ArrayList< Pair<String, TypeMirror>>();

		// replace placeholder ${ } with replaceValue
		{
			Matcher matcher = PARAMETER.matcher(whereStatement);

			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(buffer, replaceValue);
			}
			matcher.appendTail(buffer);

			whereStatement = buffer.toString();
		}
		
		return whereStatement;
	}

	/**
	 * Convert java property typeName in sql column typeName.
	 */
	static Converter<String, String> field2ColumnConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

	/**
	 * Convert java property typeName in sql column typeName.
	 */
	static Converter<String, String> field2ColumnNameFromTableConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

	/**
	 * Obtain column typeName for property
	 * 
	 * @param property
	 * @return column typeName
	 */
	public static String getColumnName(ModelProperty property) {
		return getColumnName(property.getName());
	}

	/**
	 * Obtain column typeName for property
	 * 
	 * @return column typeName
	 */
	public static String getColumnName(String propertyName) {
		return field2ColumnConverter.convert(propertyName);
	}

	public static String nameFromTable(SQLEntity entity, SQLProperty property) {
		return entity.getSimpleName() + "Table." + field2ColumnNameFromTableConverter.convert(property.getName());
	}

}
