package com.abubusoft.kripton.processor.core;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class ModelClass<E extends ModelProperty> extends ModelBucket<E, TypeElement> implements ModelElement, ModelWithAnnotation {

	protected List<ModelAnnotation> annotations;

	public ModelClass(TypeElement element) {
		this(element.getQualifiedName().toString(), element);
	}

	public ModelClass(String name, TypeElement element) {
		super(name, element);

		this.annotations = new ArrayList<ModelAnnotation>();
	}

	public String getSimpleName() {
		return element.getSimpleName().toString();
	}

	@Override
	public void accept(ModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}

	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		String canonicalName = value.getCanonicalName();

		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(canonicalName)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * @return the annotations
	 */
	public List<ModelAnnotation> getAnnotations() {
		return annotations;
	}

	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}

	public boolean containsAnnotation(Class<? extends Annotation> annotation) {
		return getAnnotation(annotation) != null;
	}

	@Override
	public boolean hasAnnotation(Class<? extends Annotation> annotationClazz) {
		return getAnnotation(annotationClazz)!=null;
	}

}
