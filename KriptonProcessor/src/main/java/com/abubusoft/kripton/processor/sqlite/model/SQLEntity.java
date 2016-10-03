package com.abubusoft.kripton.processor.sqlite.model;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;

public class SQLEntity extends ModelClass<SQLProperty> {

	private String tableName;

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

	public String getTableName() {		
		return tableName;
	}

	public void buildTableName(Elements elementUtils, SQLiteDatabaseSchema model) {
		tableName=getSimpleName();
		if (containsAnnotation(BindTable.class))			
		{
			String temp=AnnotationUtility.extractAsString(elementUtils, getElement(), BindTable.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
			
			if (temp!=null && temp.length()>0)
				tableName=temp;
		}		
		tableName=model.classNameConverter.convert(tableName);
		
	}

}
