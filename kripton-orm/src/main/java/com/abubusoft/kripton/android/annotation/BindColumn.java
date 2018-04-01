/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import com.abubusoft.kripton.android.sqlite.NoForeignKey;

/**
 * <p>
 * Allow to define specific behaviour of a field treated like a SQLite table
 * column.
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindColumn {

	static boolean NULLABLE_DEFAULT = true;

	/**
	 * If true, indicates that attribute must be binded to a column on bean's
	 * associated table
	 * 
	 * @return true to create a column for this attribute
	 */
	boolean enabled()

	default true;

	/**
	 * Name of the column. If not present, the column name is same of field. It
	 * must be specified in java style naming conventions.
	 * 
	 * @return name of the column
	 */
	public String value()

	default "";

	/**
	 * Type of column
	 * 
	 * @return type of the column
	 */
	public ColumnType columnType()

	default ColumnType.STANDARD;

	/**
	 * if true, column can be set to null
	 * 
	 * @return if true, column can be set to null
	 */
	public boolean nullable() default NULLABLE_DEFAULT;

	/**
	 * Represents foreign key to another entity/table. It can be used only on
	 * long/Long column type
	 * 
	 * @return foreign entity class to reference
	 */
	public Class<?> foreignKey() default NoForeignKey.class;

	/**
	 * Action to take on foreign key constraint during DELETE operation
	 * 
	 * @return action to take
	 */
	public ForeignKeyAction onDelete() default ForeignKeyAction.NO_ACTION;

	/**
	 * Action to take on foreign key constraint during UPDATE operation
	 * 
	 * @return action to take
	 */
	public ForeignKeyAction onUpdate() default ForeignKeyAction.NO_ACTION;

}
