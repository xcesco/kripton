package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.android.ColumnType;

/**
 * 
 * F
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindColumn {

	static boolean NULLABLE_DEFAULT = true;

	/**
	 * Name of the column. If not present, the column name is same of field. It must be specified in java style naming conventions.
	 * 
	 * @return name of the column
	 */
	public String name() default "";

	/**
	 * Type of column
	 * 
	 * @return type of the column
	 */
	public ColumnType value() default ColumnType.STANDARD;

	/**
	 * if true, column can be set to null
	 * 
	 * @return if true, column can be set to null
	 */
	public boolean nullable() default NULLABLE_DEFAULT;

}
