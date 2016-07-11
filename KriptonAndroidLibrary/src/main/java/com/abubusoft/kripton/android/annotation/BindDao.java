package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.annotation.BindType;

/**
 * This annotation mark an interface for DAO (Data Access Object) associated to a specific bean associated with {@link #value()} attribute. 
 * For each defined methods in the associated interface will be generated an method which use SQL statement. The kind of SQL used in methods will
 * depends from annotation used to mark method.
 * 
 * <p>
 * Supported query is:
 * <ul>
 * 	<li><code>INSERT</code>: with {@link BindInsert} annotation</li>
 *  <li><code>UPDATE</code></li>
 *  <li><code>SELECT</code></li>
 *  <li><code>DELETE</code></li>
 * </ul> 
 * 
 * <p>Referred {@link #value()} bean must be annotated with {@link BindType} annotation.</p>
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BindDao {

	/**
	 * <p>Bean class to associate with this dao definition.</p>
	 * 
	 * <p>Referred {@link #value()} bean must be annotated with {@link BindType} annotation.</p>
	 * 
	 * @return
	 * 		class of assocaited bean
	 */
	Class<?> value();

}
