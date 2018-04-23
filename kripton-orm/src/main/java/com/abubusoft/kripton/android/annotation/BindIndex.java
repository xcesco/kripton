/**
 * 
 */
package com.abubusoft.kripton.android.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used to define table's indexes. It uses field name to define
 * indexes. Kripton will convert field name in column name at compile time.
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
