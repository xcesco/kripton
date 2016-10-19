package com.abubusoft.kripton.processor.sqlite.model;

import java.lang.ref.WeakReference;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.processor.core.ModelProperty;

public class SQLProperty extends ModelProperty {

	public SQLProperty(SQLEntity entity, Element element) {
		super(element);
		this.parent=new WeakReference<SQLEntity>(entity);
	}
	
	protected boolean nullable;
	
	protected WeakReference<SQLEntity> parent;
	
	public SQLEntity getParent()
	{
		return parent.get();
	}
	
	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;		
	}

	/**
	 * @return the nullable
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * @param nullable the nullable to set
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * @return the primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	protected boolean primaryKey;

}
