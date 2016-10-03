package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Bind bean to shared preferences. Not all kind of field are supported.
 * 
 * <table>
 * 
 * <tr>
 * <td>Type</td>
 * <td></td>
 * <td></td>
 * </tr>
 * </table>
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindSharedPreferences {

	String name() default "";

	boolean allFields() default true;
}
