package com.abubusoft.kripton.processor.sqlite.model;

import com.abubusoft.kripton.common.StringUtils;

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

	public boolean isStaticOrderByClause() {
		return staticOrderByClause;
	}

	public void setStaticOrderByClause(boolean staticOrderByClause) {
		this.staticOrderByClause = staticOrderByClause;
	}

	public boolean hasStaticWhereClause() {
		return staticWhereClause;
	}

	public void setStaticWhereClause(boolean staticWhereClause) {
		this.staticWhereClause = staticWhereClause;
	}
	
	public boolean hasDynamicWhereConditions() {
		return StringUtils.hasText(dynamicWhereParameterName);
	}
	
	public boolean hasDynamicOrderByConditions() {
		return StringUtils.hasText(dynamicOrderByParameterName);
	}
	
	public boolean hasDynamicPageSizeConditions() {
		return StringUtils.hasText(dynamicPageSizeName);
	}

	
	public boolean isThisDynamicPageSizeName(String parameterName) {
		return StringUtils.hasText(dynamicPageSizeName) && parameterName.equals(dynamicPageSizeName);
	}



}
