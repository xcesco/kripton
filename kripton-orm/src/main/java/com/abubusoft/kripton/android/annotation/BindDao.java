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

// TODO: Auto-generated Javadoc
/**
 * <p>
 * This annotation mark an interface for DAO (Data Access Object) associated to
 * a specific bean associated with {@link #value()} attribute. For each defined
 * methods in the associated interface will be generated an method which use SQL
 * statement. The kind of SQL used in methods will depends from annotation used
 * to mark method.
 * </p>
 * 
 * <p>
 * Supported queries are:
 * </p>
 * <ul>
 * <li><code>INSERT</code>: with {@link BindSqlInsert} annotation</li>
 * <li><code>UPDATE</code>: with {@link BindSqlUpdate} annotation</li>
 * <li><code>SELECT</code>: with {@link BindSqlSelect} annotation</li>
 * <li><code>DELETE</code>: with {@link BindSqlDelete} annotation</li>
 * </ul>
 * 
 * <p>
 * Referred {@link #value()} bean must be annotated with {@link BindSqlType}
 * annotation.
 * </p>
 * 
 * <h2>Attributes</h2>
 * <ul>
 * <li><strong>value</strong>: bean class to associate with this dao
 * definition.</li>
 * </ul>
 * 
 * <h2>Usage</h2>
 * <p>
 * Suppose we have the following DaoBean03 DAO interface
 * </p>
 * 
 * <pre>
 * &#64;BindDao(Bean03.class)
 * public interface DaoBean03 {
 * 
 * 	&#64;BindSqlSelect(where = "id=${id}")
 * 	Bean03 selectOne(long id);
 * 
 * 	&#64;BindSqlDelete(where = "id=${id}")
 * 	long deleteOne(long id);
 * }
 * </pre>
 * <p>
 * And Bean03 definition:
 * </p>
 * 
 * <pre>
 * &#64;BindType
 * public class Bean03 {
 * 
 * 	&#64;Bind
 * 	&#64;BindColumn(columnType = ColumnType.PRIMARY_KEY)
 * 	protected Long id;
 * 
 * 	public Long getId() {
 * 		return id;
 * 	}
 * 
 * 	public void setId(Long id) {
 * 		this.id = id;
 * 	}
 * 
 * 	&#64;Bind
 * 	&#64;BindColumn
 * 	protected String text;
 * 
 * 	public String getText() {
 * 		return text;
 * 	}
 * 
 * 	public void setText(String text) {
 * 		this.text = text;
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * When Kripton Annotation Processor evaluate DaoBean03, it generate the
 * following DAO implementations:
 * </p>
 * 
 * <pre>
 * public class DaoBean03Impl extends AbstractDao implements DaoBean03 {
 * 	public DaoBean03Impl(BindDummy03DataSource dataSet) {
 * 		super(dataSet);
 * 	}
 * 
 * 	&#64;Override
 * 	public Bean03 selectOne(long id) {
 * 		// build where condition
 * 		String[] args = { String.valueOf(id) };
 * 
 * 		Logger.info(StringUtils.formatSQL("SELECT id, text FROM bean03 WHERE id='%s'"), (Object[]) args);
 * 		Cursor cursor = database().rawQuery("SELECT id, text FROM bean03 WHERE id=?", args);
 * 		Logger.info("Rows found: %s", cursor.getCount());
 * 
 * 		Bean03 resultBean = null;
 * 
 * 		if (cursor.moveToFirst()) {
 * 
 * 			int index0 = cursor.getColumnIndex("id");
 * 			int index1 = cursor.getColumnIndex("text");
 * 
 * 			resultBean = new Bean03();
 * 
 * 			if (!cursor.isNull(index0)) {
 * 				resultBean.setId(cursor.getLong(index0));
 * 			}
 * 			if (!cursor.isNull(index1)) {
 * 				resultBean.setText(cursor.getString(index1));
 * 			}
 * 
 * 		}
 * 		cursor.close();
 * 
 * 		return resultBean;
 * 	}
 * 
 * 	&#64;Override
 * 	public long deleteOne(long id) {
 * 		String[] whereConditions = { String.valueOf(id) };
 * 
 * 		Logger.info(StringUtils.formatSQL("DELETE bean03 WHERE id=%s"), (Object[]) whereConditions);
 * 		int result = database().delete("bean03", "id=?", whereConditions);
 * 		return result;
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * DAO implementations contains null checks and javadoc for every method that
 * are present in DAO interface and log for executed query (if in DataSource
 * attribute log is set to true).
 * </p>
 * 
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindDao {

	/**
	 * <p>
	 * Bean class to associate with this dao definition.
	 * </p>
	 * 
	 * <p>
	 * Referred {@link #value()} bean must be annotated with {@link BindSqlType}
	 * annotation.
	 * </p>
	 * 
	 * @return class of assocaited bean
	 */
	Class<?> value();

}
