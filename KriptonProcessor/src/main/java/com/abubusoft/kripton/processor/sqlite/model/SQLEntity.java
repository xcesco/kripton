package com.abubusoft.kripton.processor.sqlite.model;

import java.lang.annotation.Annotation;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.processor.core.ModelClass;

public class SQLEntity extends ModelClass<SQLProperty> {

	public SQLEntity(TypeElement element) {
		super(element);
	}

	/**
	 * Check how many PK are defined in entity. Only one field can be PK.
	 * 
	 * @return number of PK
	 */
	public int countPrimaryKeys() {
		int countAnnotation = 0;

		for (SQLProperty item : collection) {
			if (item.isPrimaryKey())
			{
				countAnnotation++;
			}
		}

		return countAnnotation;
	}

	/**
	 * True if there is a primary key
	 * 
	 * @return true if there is a primary key
	 */
	public SQLProperty getPrimaryKey() {
		for (SQLProperty item : collection) {
			if (item.isPrimaryKey())
			{
				return item;
			}			
		}

		// try to get id
		SQLProperty id = findByName("id");

		return id;
	}

	public boolean containsAnnotation(Class<? extends Annotation> annotation) {
		return getAnnotation(annotation) != null;
	}

}
