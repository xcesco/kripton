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
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.exceptions.MethodParameterNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;


/**
 * The Class SqlUtility.
 */
public class SqlUtility {
	
	/** The Constant PARAMETER. */
	private static final Pattern PARAMETER = Pattern.compile(SqlAnalyzer.PARAM_PATTERN);

	/** The Constant WORD. */
	private static final Pattern WORD = Pattern.compile("([_a-zA-Z]\\w*)");
	
	

	/**
	 * Extract from value string every placeholder :{}, replace it with ? and
	 * then convert every field typeName with column typeName. The result is a
	 * pair: the first value is the elaborated string. The second is the list of
	 * parameters associated to ?. This second parameter is the list of
	 * parameters and replaced with ?.
	 *
	 * @param value the value
	 * @param method the method
	 * @param entity the entity
	 * @return Pair 
	 */
	public static Pair<String, List<Pair<String, TypeName>>> extractParametersFromString(String value, SQLiteModelMethod method, SQLiteEntity entity) {
		String whereStatement = value;
		Pair<String, List<Pair<String, TypeName>>> result = new Pair<String, List<Pair<String, TypeName>>>();
		result.value1 = new ArrayList<Pair<String, TypeName>>();

		// replace placeholder :{ } with ?
		{
			Matcher matcher = PARAMETER.matcher(whereStatement);

			String paramName;
			StringBuffer buffer = new StringBuffer();
			TypeName paramType;			
			while (matcher.find()) {
				matcher.appendReplacement(buffer, "?");
												
				paramName = SqlAnalyzer.extractParamName(matcher);
				paramType = method.findParameterTypeByAliasOrName(paramName);

				if (paramType == null) {
					throw (new MethodParameterNotFoundException(method, paramName));
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
				ModelProperty property = entity.findPropertyByName(matcher.group(1));
				if (property != null) {
					matcher.appendReplacement(buffer, entity.findPropertyByName(matcher.group(1)).columnName);
				}

			}
			matcher.appendTail(buffer);
			whereStatement = buffer.toString();
		}

		result.value0 = whereStatement;

		return result;
	}

	/**
	 * Define collection.
	 *
	 * @param listClazzName
	 *            the list clazz name
	 * @return the type name
	 */
	public static ClassName defineCollection(TypeName listClazzName) {
		try {
			Class<?> clazz=null;
			if (listClazzName instanceof ParameterizedTypeName) {
				ParameterizedTypeName returnListName = (ParameterizedTypeName) listClazzName;
				clazz = Class.forName(returnListName.rawType.toString());
			} else {
				clazz = Class.forName(listClazzName.toString());	
			}						

			if (clazz.isAssignableFrom(List.class)) {
				clazz = ArrayList.class;
			} else if (clazz.isAssignableFrom(Set.class)) {
				clazz = LinkedHashSet.class;
			} else if (clazz.isAssignableFrom(Collection.class)) {
				clazz = ArrayList.class;
			}

			return ClassName.get(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}
	

}
