package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.binder.database.ColumnType;

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

	public final static boolean NULLABLE_DEFAULT = true;

	/**
	 * Name of the column. If not present, the column name is same of field. It
	 * must be specified in java style naming conventions.
	 * 
	 * @return
	 */
	public String name() default "";

	/**
	 * Type of column
	 * @return
	 */
	public ColumnType value() default ColumnType.STANDARD;

	/**
	 * If true the column can be nullable.
	 * @return
	 */
	public boolean nullable() default NULLABLE_DEFAULT;

}
