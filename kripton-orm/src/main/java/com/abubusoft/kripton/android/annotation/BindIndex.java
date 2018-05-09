/**
 * 
 */
package com.abubusoft.kripton.android.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Annotation used to define table's indexes. It can be used in
 * {@link BindSqlType} annotation.
 * </p>
 * 
 * <h3>Attributes</h3>
 * <ul>
 * <li><strong>value</strong>: an array of fields used for index definition.
 * <strong>You must use field name: Kripton will translate field names in column
 * names at compile time</strong>. Every field can be followed by `asc` or `desc`
 * keyword. Follow this <a href="https://www.sqlite.org/lang_createindex.html">link</a> for
 * more information.</li>
 * <li><strong>unique</strong>: if true, it defines the index as a unique index:
 * the index cannot have the same value for different rows.</li>
 * </ul>
 * 
 * 
 * <h3>Usage</h3>
 * 
 * <pre>
&#64;BindType
&#64;BindSqlType(
  indexes= {
    &#64;BindIndex({"birthCity", "birthDay desc"}),
    &#64;BindIndex({"surname"}),
    &#64;BindIndex(value={"name","surname", "date desc"}, unique=true )
  }
)
public class Person {
  ...
}
 * </pre>
 * 
 * The above data model definition will generate the following table definition:
 * 
 * <pre>
CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, alias_name TEXT UNIQUE, date TEXT, name TEXT, surname TEXT, birth_city TEXT, birth_day TEXT); 
CREATE INDEX idx_person_name ON person(name); CREATE INDEX idx_person_surname ON person(surname); 
CREATE UNIQUE INDEX idx_person_0 on person (name, surname, date desc); 
CREATE INDEX idx_person_0 on person (birth_city, birth_day desc); 
CREATE INDEX idx_person_1 on person (surname);
 * </pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(CLASS)
@Target(ANNOTATION_TYPE)
public @interface BindIndex {

	/**
	 * set of field name used to define index. Field names will be converted at
	 * compile time
	 * 
	 * @return field set that define index.
	 */
	String[] value();

	/**
	 * Indicates if index is unique or not.
	 * 
	 * @return true if index is unique
	 */
	boolean unique() default false;
}
