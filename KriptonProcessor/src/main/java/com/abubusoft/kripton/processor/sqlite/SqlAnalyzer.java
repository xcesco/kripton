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
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.MethodParameterNotFoundException;
import com.abubusoft.kripton.processor.exceptions.PropertyInAnnotationNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.TypeName;

/**
 * Analyze an SQL statement, extract parameter and replace with ?
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class SqlAnalyzer {

	private final Pattern PARAMETER = Pattern.compile("\\$\\{\\s*([\\w._]*)\\s*\\}");

	private final Pattern WORD = Pattern.compile("([_a-zA-Z]\\w*)");

	Converter<String, String> propertyConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	private List<String> paramNames;
	
	private List<TypeName> paramTypeNames;
	
	/**
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
	 * @return the paramNames
	 */
	public List<String> getParamNames() {
		return paramNames;
	}

	/**
	 * @return the paramGetters
	 */
	public List<String> getParamGetters() {
		return paramGetters;
	}

	private List<String> paramGetters;

	private String sqlStatement;
	
	/**
	 * used method parameter.
	 */
	private Set<String> usedMethodParameters;

	/**
	 * @return the usedMethodParameters
	 */
	public Set<String> getUsedMethodParameters() {
		return usedMethodParameters;
	}

	/**
	 * Extract from value string every placeholder ${}, replace it with ? and then convert every field typeName with column typeName. The result is a pair: the first value is the elaborated string. The second is the list of parameters associated to
	 * ?. This second parameter is the list of parameters and replaced with ?.
	 * 
	 */
	public void execute(Elements elementUtils, SQLiteModelMethod method, String sqlStatement) {
		SQLDaoDefinition daoDefinition=method.getParent();
		SQLEntity entity=daoDefinition.getEntity();
		
		usedMethodParameters=new HashSet<String>();		
		
		paramNames = new ArrayList<String>();
		paramGetters = new ArrayList<String>();
		usedBeanPropertyNames=new ArrayList<String>();
		paramTypeNames=new ArrayList<TypeName>();

		// replace placeholder ${ } with ?
		{
			Matcher matcher = PARAMETER.matcher(sqlStatement);

			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(buffer, "?");
				paramNames.add(matcher.group(1));
			}
			matcher.appendTail(buffer);

			sqlStatement = buffer.toString();
		}

		// replace property typeName to column typeName
		{
			Matcher matcher = WORD.matcher(sqlStatement);

			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				ModelProperty property = entity.findByName(matcher.group(1));
				if (property != null) {
					matcher.appendReplacement(buffer, SqlUtility.getColumnName(matcher.group(1)));
				}

			}
			matcher.appendTail(buffer);
			sqlStatement = buffer.toString();
		}
		
		TypeName rawNameType;
		// analyze parametersName
		String[] splittedName;
		String effectiveName;
		for (String rawName: paramNames)
		{
			splittedName=rawName.split("\\.");
			
			if (splittedName.length==1)
			{
				effectiveName=method.findParameterNameByAlias(rawName);
				rawNameType = method.findParameterTypeByAliasOrName(effectiveName);
				if (rawNameType==null)
				{
					throw new MethodParameterNotFoundException(method, effectiveName);
				}
				paramGetters.add(effectiveName);
				paramTypeNames.add(rawNameType);
				
				usedMethodParameters.add(effectiveName);
			} else if (splittedName.length==2) {
				//TODO verify
				if (method.findParameterTypeByAliasOrName(splittedName[0])==null)
				{
					throw new MethodParameterNotFoundException(method, splittedName[0]);
				}
				 
				if (TypeUtility.isEquals(method.findParameterTypeByAliasOrName(splittedName[0]), entity) && entity.contains(splittedName[1]))
				{				
					// there are nested property invocation
					paramGetters.add(method.findParameterNameByAlias(splittedName[0])+"."+getter(entity.findByName(splittedName[1])));
					usedBeanPropertyNames.add(splittedName[1]);
					//paramTypeNames.add(entity.findByName(splittedName[1]).getPropertyType());
					paramTypeNames.add(TypeUtility.typeName(entity.findByName(splittedName[1]).getElement().asType()));
					
					usedMethodParameters.add(method.findParameterNameByAlias(splittedName[0]));
				} else {
					throw (new PropertyInAnnotationNotFoundException(method, splittedName[1]));
				}
			} else {
				throw (new PropertyInAnnotationNotFoundException(method, rawName));
			}
			
		}
				
		this.sqlStatement=sqlStatement;
	}

	/**
	 * @return the usedBeanProperties
	 */
	public List<String> getUsedBeanPropertyNames() {
		return usedBeanPropertyNames;
	}

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

	public String setter(ModelProperty property) {
		if (property.isPublicField())
			return property.getName();

		if (property.isFieldWithGetter() || property.isFieldWithIs()) {
			return "set" + propertyConverter.convert(property.getName());
		}

		return null;
	}

	public String getSQLStatement() {
		return sqlStatement;
	}
}
