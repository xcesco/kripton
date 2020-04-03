package com.abubusoft.kripton.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER })
public @interface BindXmlElement {
	/**
	 * Name of the XML Schema element.
	 * <p>
	 * If the value is "", then element name is derived from the JavaBean property
	 * name.
	 */
	String name() default "";

	/**
	 * The Java class being referenced. The class must be annotated with {@link BindType} annotation.
	 */
	Class<?> type();
}