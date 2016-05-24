package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.MethodParameterNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;

/**
 * Analyze an SQL statement, extract parameter and replace with ?
 * 
 * @author xcesco
 *
 */
public class SQLAnalyzer {

	private final Pattern PARAMETER = Pattern.compile("\\$\\{\\s*([\\w._]*)\\s*\\}");

	private final Pattern WORD = Pattern.compile("([_a-zA-Z]\\w*)");

	Converter<String, String> propertyConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	private List<String> paramNames;
	
	/**
	 * bean properties name used into statement.
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
	 * Extract from value string every placeholder ${}, replace it with ? and then convert every field name with column name. The result is a pair: the first value is the elaborated string. The second is the list of parameters associated to
	 * ?. This second parameter is the list of parameters and replaced with ?.
	 * 
	 */
	public void execute(Elements elementUtils, SQLDaoDefinition daoDefinition, SQLEntity entity, ModelMethod method, String sqlStatement) {
		paramNames = new ArrayList<String>();
		paramGetters = new ArrayList<String>();
		usedBeanPropertyNames=new ArrayList<String>();

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

		// replace property name to column name
		{
			Matcher matcher = WORD.matcher(sqlStatement);

			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				ModelProperty property = entity.findByName(matcher.group(1));
				if (property != null) {
					matcher.appendReplacement(buffer, SQLUtility.getColumnName(matcher.group(1)));
				}

			}
			matcher.appendTail(buffer);
			sqlStatement = buffer.toString();
		}
		
		// analyze parametersName
		String[] splittedName;
		for (String rawName: paramNames)
		{
			splittedName=rawName.split("\\.");
			
			if (splittedName.length==1)
			{
				paramGetters.add(rawName);
			} else if (splittedName.length==2) {
				if (TypeUtility.isEquals(TypeUtility.typeName(method.findParameter(splittedName[0])), entity) && entity.contains(splittedName[1]))
				{				
					// there are nested property invocation
					paramGetters.add(splittedName[0]+"."+getter(entity.findByName(splittedName[1])));
					usedBeanPropertyNames.add(splittedName[1]);
					
				} else {
					throw (new MethodParameterNotFoundException(daoDefinition, method, rawName));
				}
			} else {
				throw (new MethodParameterNotFoundException(daoDefinition, method, rawName));
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
