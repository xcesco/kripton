package com.abubusoft.kripton.processor.core;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class ModelClass extends ModelBucket<ModelProperty, TypeElement> implements ModelElement, ModelWithAnnotation {
	
	protected List<ModelAnnotation> annotations;
	
	public ModelClass(TypeElement element) {
		super(element.getQualifiedName().toString(), element);
		
		this.annotations = new ArrayList<ModelAnnotation>();
	}
	
	public String getSimpleName()
	{
		return element.getSimpleName().toString();
	}

	@Override
	public void accept(ModelElementVisitor visitor) throws Exception {
		visitor.visit(this);		
	}

	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(value.getCanonicalName())) {
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

}
