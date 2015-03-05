package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * This annotation maps a POJO field to any XML element,
 * Note, this annotation can only be applied on a @see java.util.List field, parametrized type must be @see java.lang.Object,
 * and the entry type can be either a Nano bindable class or a DOM element.
 * 
 * @author bulldog
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindAnyElement {

}
