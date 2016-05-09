package com.abubusoft.kripton.processor.sqlite.model;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.processor.core.ModelBucket;

public class DaoDefinition extends ModelBucket<SQLiteModelMethod, TypeElement> implements SQLiteModelElement {

	private String entityClassName;

	private String entitySimplyClassName;

	/**
	 * @return the entitySimplyClassName
	 */
	public String getEntitySimplyClassName() {
		return entitySimplyClassName;
	}

	/**
	 * @return the entityClassName
	 */
	public String getEntityClassName() {
		return entityClassName;
	}

	public String getSimpleEntityClassName() {
		return entitySimplyClassName;
	}

	public DaoDefinition(TypeElement element, String entityClassName) {
		super(element.getSimpleName().toString(), element);
		this.entityClassName = entityClassName;

		int i = 0;
		i = entityClassName.indexOf(".");

		if (i > 0) {
			entitySimplyClassName = entityClassName.substring(entityClassName.lastIndexOf(".")+1);
		} else {
			entitySimplyClassName = entityClassName;
		}
	}

	@Override
	public void accept(SQLiteModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}

}
