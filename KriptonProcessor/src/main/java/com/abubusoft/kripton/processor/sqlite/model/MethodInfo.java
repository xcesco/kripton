package com.abubusoft.kripton.processor.sqlite.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.common.StringUtils;
import com.squareup.javapoet.TypeName;

/**
 * <p>
 * Contains info about method such as if it has WHERE conditions, etc.
 * </p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class MethodInfo {
	
	public enum MethodType {
		SQL_INSERT,
		SQL_SELECT,
		SQL_UPDATE,
		SQL_DELETE
	}
	
	public MethodType type;
	
	
	/**
	 * <p>
	 * It is the typeName of parameter used to dynamic where condition (defined at
	 * runtime).
	 * </p>
	 */
	public String dynamicWhereParameterName;
	
	/**
	 * <p>
	 * It is the typeName of parameter used to dynamic order by condition (defined at
	 * runtime).
	 * </p>
	 */
	public String dynamicOrderByParameterName;

	/**
	 * <p>
	 * It is the typeName of parameter used to dynamic page size (defined at
	 * runtime).
	 * </p>
	 */
	public String dynamicPageSizeName;

	
	protected boolean staticWhereClause;
	
	protected boolean staticOrderByClause;

	/**
	 * <p>JAVA SQL. It is the SQL code build with Java convention and dynamic part.For example:</p>
	 * 
	 * <pre>SELECT id, action, number, countryCode, contactName, contactId FROM phone_number WHERE number = ${bean.number} and number = ${bean.number} and #{dynamicWhere}</pre>
	 */
	public String jql;

	protected Map<String, MethodParameterInfo> parametersByAlias;

	protected Map<String, MethodParameterInfo> parametersByName;
	
	protected List<MethodParameterInfo> parameters=new ArrayList<>();

	public boolean hasDynamicOrderByConditions() {
		return StringUtils.hasText(dynamicOrderByParameterName);
	}

	public boolean hasDynamicPageSizeConditions() {
		return StringUtils.hasText(dynamicPageSizeName);
	}
	
	public boolean hasDynamicWhereConditions() {
		return StringUtils.hasText(dynamicWhereParameterName);
	}
	
	public boolean hasStaticWhereClause() {
		return staticWhereClause;
	}
	
	public boolean isStaticOrderByClause() {
		return staticOrderByClause;
	}

	
	public boolean isThisDynamicPageSizeName(String parameterName) {
		return StringUtils.hasText(dynamicPageSizeName) && parameterName.equals(dynamicPageSizeName);
	}

	public void setStaticOrderByClause(boolean staticOrderByClause) {
		this.staticOrderByClause = staticOrderByClause;
	}

	public void setStaticWhereClause(boolean staticWhereClause) {
		this.staticWhereClause = staticWhereClause;
	}
	
	/**
	 * Retrieve for a method's parameter its alias, used to work with queries.
	 * If no alias is present, typeName will be used.
	 * 
	 * @param typeName
	 * @return
	 */
	public String findParameterAliasByName(String name) {
		if (parametersByName.containsKey(name)) {
			return parametersByName.get(name).alias;
		}

		return name;
	}
	
	/**
	 * Check if method contains a parameter with value as typeName
	 * 
	 * @param nameOrAlias
	 *            parameter typeName to find
	 * @return TypeMirror associated
	 */
	public String findParameterNameByAlias(String nameOrAlias) {
		String[] arrays = nameOrAlias.split("\\.");
		String suffix = "";

		if (arrays.length == 2) {
			nameOrAlias = arrays[0];
			suffix = "." + arrays[1];

		}

		if (parametersByAlias.containsKey(nameOrAlias)) {
			return parametersByAlias.get(nameOrAlias).name + suffix;
		} else {
			return parametersByName.get(nameOrAlias).name + suffix;
		}
		
	}
	
	/**
	 * Check if method contains a parameter with value as typeName
	 * 
	 * @param typeName
	 *            parameter typeName to find
	 * @return TypeMirror associated
	 */
	public TypeName findParameterTypeByAliasOrName(String name) {
		if (parametersByAlias.containsKey(name)) {
			return parametersByAlias.get(name).typeName;
		}

		return parametersByName.get(name).typeName;
	}


}
