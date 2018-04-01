/**
 * 
 */
package com.abubusoft.kripton.android.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;

@Retention(CLASS)
@Target(ANNOTATION_TYPE)
/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public @interface BindDataSourceUpdateTask {

	int version();
	
	Class<? extends SQLiteUpdateTask> task();
}
