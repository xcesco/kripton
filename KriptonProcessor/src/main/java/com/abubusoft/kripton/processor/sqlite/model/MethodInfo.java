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
	
	/**
	 * <p>
	 * It is the typeName of parameter used to dynamic where condition (defined at
	 * runtime).
	 * </p>
	 */
	public String dynamicWhereParameterName;
	
	protected boolean staticWhereClause;
	
	protected boolean staticOrderByClause;

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


}
