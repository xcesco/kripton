package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.android.sharedprefs.PreferenceType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindPreference {
	
	/**
	 * 
	 * @return
	 * 		if true, means field must bind persist model
	 */
	boolean enabled() default true;

	/**
	 * 
	 * 
	 * @return
	 * 		Preference name. Default name is attribute name.
	 */
	String name() default "";


	/**
	 * 
	 * 
	 * @return
	 * 		Preference type. Usually it is a string.
	 */
	PreferenceType preferenceType() default PreferenceType.STRING;

	/**
	 * @return
	 * 		if true, field are copied while reset. Otherwise it will be ignored.
	 */
	boolean copyOnReset() default true;

	/**
	 * If true, field will be cripted. It will be applyed only for string convertible field.
	 * 
	 * @return
	 * 		if true, field will be cripted.
	 */
	boolean crypted() default false;

}
