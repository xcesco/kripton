package com.abubusoft.kripton.processor.sqlite.model;

import java.lang.ref.WeakReference;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelBucket;

public class SQLDaoDefinition extends ModelBucket<SQLiteModelMethod, TypeElement> implements SQLiteModelElement {
	
	private WeakReference<SQLiteDatabaseSchema> parent;

	/**
	 * @return the parent
	 */
	public SQLiteDatabaseSchema getParent() {
		return parent.get();
	}

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

	public SQLDaoDefinition(SQLiteDatabaseSchema databaseSchema, TypeElement element, String entityClassName) {
		super(element.getSimpleName().toString(), element);
		this.parent=new WeakReference<SQLiteDatabaseSchema>(databaseSchema);
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

	public SQLEntity getEntity() {
		 return getParent().getEntity(getEntityClassName());
	}

	public Converter<String, String> getColumnNameConverter() {
		return getParent().columnNameConverter;
	}

	public Converter<String, String> getClassNameConverter() {
		return getParent().classNameConverter;
	}

	/**
	 * Return true if log must be generated.
	 * 
	 * @return
	 * 	Return true if log must be generated.
	 */
	public boolean isLogEnabled() {
		return getParent().log;
	}

}
