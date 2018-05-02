/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

import com.abubusoft.kripton.android.SqlTypeAdapter;

/**
 * <p>
 * The Interface BindSqlAdapter. This annotation decorates a field to use a
 * particular SQL Type Adapter to customize persistence on an SQLite table. A
 * type adapter must implements SqlTypeAdapter interface. It has two parameter
 * type: the first is the field type (in our example is Bitmap), the second is
 * the type that we want to use as replacement and that will be used to store
 * data into an SQLite table column. It implements three methods:
 * </p>
 * 
 * <ul>
 * <li><code>toJava</code>: converts data retrieved from an SQLite table into a
 * field.</li>
 * <li><code>toData</code>: converts a field into data to store into an SQLite
 * table.</li>
 * <li><code>toString</code>: used if you want to use a field as query's
 * parameter (SQLite wrapper for Android convert all SQL parameter used in where
 * condition in its string representation). Attributes</li> adapter: SQLite Type
 * Adapter class
 * <h3>Usage</h3>
 * 
 * <pre>
 * &#64;BindTable
 * public class Person {
 * 	public long id;
 * 
 * 	&#64;BindSqlAdapter(adapter = BitmapTypeAdapter.class)
 * 	public Bitmap image;
 * }
 * </pre>
 * 
 * <p>
 * And the associated SQL type adapter:
 * </p>
 * 
 * <pre>
 * public class BitmapTypeAdapter implements SqlTypeAdapter&lt;Bitmap, byte[]&gt; {
 * 
 * 	&#64;Override
 * 	public Bitmap toJava(byte[] dataValue) {
 * 		if (dataValue == null)
 * 			return null;
 * 		return BitmapFactory.decodeByteArray(dataValue, 0, dataValue.length);
 * 	}
 * 
 * 	&#64;Override
 * 	public byte[] toData(Bitmap bitmap) {
 * 		if (bitmap == null)
 * 			return null;
 * 
 * 		ByteArrayOutputStream stream = new ByteArrayOutputStream();
 * 		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
 * 		return stream.toByteArray();
 * 	}
 * 
 * 	&#64;Override
 * 	public String toString(Bitmap javaValue) {
 * 		throw (new KriptonRuntimeException("Unsupported operation!"));
 * 	}
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindSqlAdapter {

	/**
	 * Adapter class used to convert bean attribute into column value and
	 * viceversa.
	 *
	 * @return the adapter
	 */
	Class<? extends SqlTypeAdapter<?, ?>> adapter();
}
