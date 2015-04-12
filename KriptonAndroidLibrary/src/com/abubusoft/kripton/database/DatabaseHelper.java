package com.abubusoft.kripton.database;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;

import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.exception.MappingException;

/**
 * Utilty
 * 
 * @author xcesco
 *
 */
public class DatabaseHelper {

	/**
	 * Iterate list of field name of table and create equivalent parameter in
	 * params.
	 * 
	 * @param params
	 *            where store results
	 * @param fieldNameList
	 *            list of field name
	 * @param table
	 *            table which contains fields
	 */
	public static void scanSchema(String name, Filter filter, DatabaseTable table) {
		DatabaseColumn column;
		int i = 0;
		for (String item : filter.fieldNames) {
			column = table.field2column.get(item);

			if (column == null)
				throw new MappingException("Field " + item + ", used in where statement of the " + name + " is not defined in class " + table.clazz.getName());

			filter.field[i] = column.schema.getField();
			filter.fieldTransform[i]=Transformer.lookup(filter.field[i].getType());
			
			i++;
		}
	}

	/**
	 * @param params
	 * @param clazz
	 */
	private static void scanFields(LinkedHashMap<String, Field> fields, Filter filter, Class<?> clazz) {
		for (Field item : clazz.getDeclaredFields()) {
			if (Modifier.isStatic(item.getModifiers()))
				continue;
			if (!item.isAccessible())
				item.setAccessible(true);

			if (!Transformer.isTransformable(item.getType())) {
				throw new MappingException("Can not use class " + item.getType() + " like params for query because field " + item.getName()
						+ " has not a string convertion.");
			}
			fields.put(item.getName(), item);
		}

	}

	public static void scanClass(String name, Filter filter, Class<?> paramsClass) {
		if (paramsClass==null) return;
		LinkedHashMap<String, Field> fields = new LinkedHashMap<String, Field>();

		scanFields(fields, filter, paramsClass);

		Class<?> superType = paramsClass.getSuperclass();
		// scan super class fields
		while (superType != Object.class && superType != null) {
			scanFields(fields, filter, superType);
			superType = paramsClass.getSuperclass();
		}
		
		int i=0;
		Field field;
		for (String item: filter.fieldNames)
		{
			field=fields.get(item);
			
			if (field==null)
			{
				throw (new MappingException("Class " + paramsClass + " does not contains field "+item));
			}
			
			filter.field[i]=field;
			filter.fieldTransform[i]=Transformer.lookup(filter.field[i].getType());
			
			i++;
		}

	}
}
