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

import com.abubusoft.kripton.android.ColumnAffinityType;
import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import com.abubusoft.kripton.android.sqlite.NoParentEntity;

/**
 * This annotation allow to customize binding from Java bean's field to SQLite
 * table's columns.
 * 
 * <h3>Attributes</h3>
 * <ul>
 * <li><strong>columnType</strong>: specifty if column is a PRIMARY_KEY, UNIQUE,
 * or STANDARD. Default value is STANDARD.</li>
 * <li><strong>enabled</strong>: if false means that associated field is not
 * binded to SQLite database table. Default value is true.</li>
 * <li><strong>foreignKey</strong>: link to entity/class linked by this field if
 * it is a foreign key. It can be used only on long/Long column type.</li>
 * <li><strong>nullable</strong> if true, column can be set to
 * <code>null</code>. Default value is <code>true</code></li>
 * <li><strong>onDelete</strong> Action to take on foreign key constraint during
 * a DELETE operation. It's used only if foreignKey is defined.</li>
 * <li><strong>onUpdate</strong> Action to take on foreign key constraint during
 * an UPDATE operation. It's used only if foreignKey is defined.</li>
 * <li><strong>value</strong>: name of the column. If not present, the column
 * name is same of field. It must be specified in java style naming conventions.
 * nullable: if true, column can be set to null. Default value is true.</li>
 * </ul>
 * 
 * <h3>Usage</h3>
 * <p>
 * Just an example:
 * </p>
 * 
 * <pre>
 * &#64;BindType
 * public class User {
 * 
 * 	&#64;BindColumn(columnType = ColumnType.PRIMARY_KEY)
 * 	public long id;
 * 
 * 	&#64;Bind(enabled = false)
 * 	public int index;
 * 
 * 	&#64;BindColumn("picture")
 * 	public String pictureUrl;
 * }
 * </pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindColumn {

	/** The nullable default. */
	static boolean NULLABLE_DEFAULT = true;

	/**
	 * If true, indicates that attribute must be binded to a column on bean's
	 * associated table.
	 *
	 * @return true to create a column for this attribute
	 */
	boolean enabled() default true;

	/**
	 * Name of the column. If not present, the column name is same of field. It
	 * must be specified in java style naming conventions.
	 * 
	 * @return name of the column
	 */
	public String value() default "";

	/**
	 * Type of column.
	 *
	 * @return type of the column
	 */
	public ColumnType columnType() default ColumnType.STANDARD;

	/**
	 * if true, column can be set to null.
	 *
	 * @return if true, column can be set to null
	 */
	public boolean nullable() default NULLABLE_DEFAULT;

	/**
	 * Indicates that this field will be used as foreign key in a relationship with the specified entity.
	 *  It can be used only on long/Long column type.
	 * 
	 * @return foreign entity class to reference
	 */
	public Class<?> parentEntity() default NoParentEntity.class;

	/**
	 * Action to take on foreign key constraint during DELETE operation.
	 *
	 * @return action to take
	 */
	public ForeignKeyAction onDelete() default ForeignKeyAction.NO_ACTION;

	/**
	 * Action to take on foreign key constraint during UPDATE operation.
	 *
	 * @return action to take
	 */
	public ForeignKeyAction onUpdate() default ForeignKeyAction.NO_ACTION;

	/**
	 * Allows to specify column affinity. Usually it was take directly from
	 * field type. Default value is AUTO.
	 * 
	 * @return type affinity for the column. AUTO value means column type is
	 *         derived from field type.
	 * 
	 */
	public ColumnAffinityType columnAffinity() default ColumnAffinityType.AUTO;

}
