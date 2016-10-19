package com.abubusoft.kripton.processor.core;

import java.lang.annotation.Annotation;

public interface ModelWithAnnotation extends ModelWrapperElement {
	void addAnnotation(ModelAnnotation annotation);

	ModelAnnotation getAnnotation(Class<? extends Annotation> value);
	
	boolean hasAnnotation(Class<? extends Annotation> annotationClazz);
}
