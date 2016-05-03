package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.binder.database.ColumnType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.ModelProperty;

public class SQLEntity extends ModelClass {

	public SQLEntity(TypeElement element) {
		super(element);
	}

	/**
	 * True if there is a primary key
	 * 
	 * @return true if there is a primary key
	 */
	public ModelProperty getPrimaryKey() {
		ModelAnnotation annotation;
		String value;

		for (ModelProperty item : collection) {
			annotation = item.getAnnotation(BindColumn.class);
			if (annotation != null) {
				value = annotation.getAttribute("value");
				if (value != null && value.contains(ColumnType.PRIMARY_KEY.toString())) {
					return item;
				}

			}
		}
		
		// try to get id
		ModelProperty id=findPropertyByName("id");			

		return id;
	}

	/**
	 * find property by its name
	 * 
	 * @param key
	 * 		simple name of property
	 * @return
	 * 		property or null
	 */
	public ModelProperty findPropertyByName(String key) {
		for (ModelProperty item : collection) {
			if (item.getName().equals(key))
			{
				return item;
			}
		}
		
		return null;
	}

}
