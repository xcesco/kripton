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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.MethodParameterNotFoundException;
import com.abubusoft.kripton.processor.exceptions.PropertyInAnnotationNotFoundException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLParameterName;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.TypeName;


/**
 * Analyze an SQL statement, extract parameter and replace with ?.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class SqlAnalyzer {
	
	public static String PARAM_PREFIX=":";
	public static String PARAM_SUFFIX="";
	
	public static String PARAM_PATTERN="(\\$\\{\\s*([\\w._]*)\\s*\\})|(\\:\\{\\s*([\\w._]*)\\s*\\})|(\\:\\s*([\\w._]*))";
	
	/**
	 * @param matcher
	 * @return
	 */
	public static String extractParamName(Matcher matcher) {
		int index;
		if (StringUtils.hasText(matcher.group(2))) {
			index=2;
		} else if (StringUtils.hasText(matcher.group(4))) {
			index=4;
		} else {
			index=6;
		}
		return matcher.group(index);
	}

	/** The parameter. */
	private final Pattern PARAMETER = Pattern.compile(PARAM_PATTERN);

	/** The word. */
	private final Pattern WORD = Pattern.compile("([_a-zA-Z]\\w*)");

	/** The property converter. */
	Converter<String, String> propertyConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	/** The param names. */
	private List<String> paramNames;
	
	/** The param type names. */
	private List<TypeName> paramTypeNames;
	
	/**
	 * Gets the param type names.
	 *
	 * @return the paramTypes
	 */
	public List<TypeName> getParamTypeNames() {
		return paramTypeNames;
	}

	/**
	 * bean properties typeName used into statement.
	 */
	private List<String> usedBeanPropertyNames;

	/**
	 * Gets the param names.
	 *
	 * @return the paramNames
	 */
	public List<String> getParamNames() {
		return paramNames;
	}

	/**
	 * Gets the param getters.
	 *
	 * @return the paramGetters
	 */
	public List<String> getParamGetters() {
		return paramGetters;
	}

	/** The param getters. */
	private List<String> paramGetters;

	/** The sql statement. */
	private String sqlStatement;
	
	/**
	 * used method parameter.
	 */
	private Set<String> usedMethodParameters;

	/**
	 * Gets the used method parameters.
	 *
	 * @return the usedMethodParameters
	 */
	public Set<String> getUsedMethodParameters() {
		return usedMethodParameters;
	}

	/**
	 * Extract from value string every placeholder :{}, replace it with ? and then convert every field typeName with column typeName. The result is a pair: the first value is the elaborated string. The second is the list of parameters associated to
	 * ?. This second parameter is the list of parameters and replaced with ?.
	 *
	 * @param elementUtils the element utils
	 * @param method the method
	 * @param sqlStatement the sql statement
	 */
	public void execute(Elements elementUtils, SQLiteModelMethod method, String sqlStatement) {
		SQLiteDaoDefinition daoDefinition=method.getParent();
		SQLiteEntity entity=daoDefinition.getEntity();
		
		usedMethodParameters=new HashSet<String>();		
		
		paramNames = new ArrayList<String>();
		paramGetters = new ArrayList<String>();
		usedBeanPropertyNames=new ArrayList<String>();
		paramTypeNames=new ArrayList<TypeName>();

		// replace placeholder :{ } with ?
		{
			Matcher matcher = PARAMETER.matcher(sqlStatement);

			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(buffer, "?");
								
				
				paramNames.add(extractParamName(matcher));
			}
			matcher.appendTail(buffer);

			sqlStatement = buffer.toString();
		}

		// replace property typeName to column typeName
		{
			Matcher matcher = WORD.matcher(sqlStatement);

			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				SQLProperty property = entity.findPropertyByName(matcher.group(1));
				if (property != null) {
					matcher.appendReplacement(buffer, property.columnName);
				}

			}
			matcher.appendTail(buffer);
			sqlStatement = buffer.toString();
		}
		
		TypeName rawNameType;
		// analyze parametersName
		String effectiveName;
		for (String rawName: paramNames)
		{
			JQLParameterName pName=JQLParameterName.parse(rawName);			
			
			if (!pName.isNested())
			{
				effectiveName=method.findParameterNameByAlias(pName.getValue());
				rawNameType = method.findParameterTypeByAliasOrName(effectiveName);
				if (rawNameType==null)
				{
					throw new MethodParameterNotFoundException(method, effectiveName);
				}
				paramGetters.add(effectiveName);
				paramTypeNames.add(rawNameType);				
				usedMethodParameters.add(effectiveName);
				usedBeanPropertyNames.add(null);
			} else {
				if (method.findParameterTypeByAliasOrName(pName.getBeanName())==null)
				{
					throw new MethodParameterNotFoundException(method, pName.getBeanName());
				}
				 
				if (TypeUtility.isEquals(method.findParameterTypeByAliasOrName(pName.getBeanName()), entity) && entity.contains(pName.getValue()))
				{				
					// there are nested property invocation
					paramGetters.add(method.findParameterNameByAlias(pName.getBeanName())+"."+getter(entity.findPropertyByName(pName.getValue())));
					usedBeanPropertyNames.add(pName.getValue());
					paramTypeNames.add(TypeUtility.typeName(entity.findPropertyByName(pName.getValue()).getElement().asType()));				
					usedMethodParameters.add(method.findParameterNameByAlias(pName.getBeanName()));
				} else {
					throw (new PropertyInAnnotationNotFoundException(method, pName.getValue()));
				}
			}
//			} else {
//				throw (new PropertyInAnnotationNotFoundException(method, rawName));
//			}
			
		}
				
		this.sqlStatement=sqlStatement;
	}

	/**
	 * Gets the used bean property names.
	 *
	 * @return the usedBeanProperties
	 */
	public List<String> getUsedBeanPropertyNames() {
		return usedBeanPropertyNames;
	}

	/**
	 * Gets the ter.
	 *
	 * @param property the property
	 * @return the ter
	 */
	public String getter(ModelProperty property) {		
		if (property.isPublicField())
			return property.getName();

		if (property.isFieldWithGetter()) {
			return "get" + propertyConverter.convert(property.getName()) + "()";
		}

		if (property.isFieldWithIs()) {
			return "is" + propertyConverter.convert(property.getName()) + "()";
		}

		return null;
	}

	/**
	 * Setter.
	 *
	 * @param property the property
	 * @return the string
	 */
	public String setter(ModelProperty property) {
		if (property.isPublicField())
			return property.getName();

		if (property.isFieldWithGetter() || property.isFieldWithIs()) {
			return "set" + propertyConverter.convert(property.getName());
		}

		return null;
	}

	/**
	 * Gets the SQL statement.
	 *
	 * @return the SQL statement
	 */
	public String getSQLStatement() {
		return sqlStatement;
	}
}
