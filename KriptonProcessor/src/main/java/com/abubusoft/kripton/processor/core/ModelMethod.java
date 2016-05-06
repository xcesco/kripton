package com.abubusoft.kripton.processor.core;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.common.Pair;

public class ModelMethod extends ModelEntity<ExecutableElement> implements ModelWithAnnotation {
	
	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(value.getCanonicalName())) {
				return item;
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModelMethod [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (annotations != null) {
			builder.append("annotations=");
			builder.append(annotations);
			builder.append(", ");
		}
		if (parameters != null) {
			builder.append("parameters=");
			builder.append(parameters);
			builder.append(", ");
		}
		if (returnClass != null) {
			builder.append("returnClass=");
			builder.append(returnClass);
			builder.append(", ");
		}
		if (element != null) {
			builder.append("element=");
			builder.append(element);
		}
		builder.append("]");
		return builder.toString();
	}

	public ModelMethod(ExecutableElement element) {
		super(element.getSimpleName().toString(), element);
		this.parameters = new ArrayList<Pair<String, TypeMirror>>();
		this.annotations = new ArrayList<ModelAnnotation>();

		for (VariableElement p : element.getParameters()) {
			parameters.add(new Pair<String, TypeMirror>(p.getSimpleName().toString(), p.asType()));
		}

		returnClass = element.getReturnType();
	}

	protected String name;

	protected List<ModelAnnotation> annotations;

	/**
	 * @return the annotations
	 */
	public List<ModelAnnotation> getAnnotations() {
		return annotations;
	}

	/**
	 * @return the parameters
	 */
	public List<Pair<String, TypeMirror>> getParameters() {
		return parameters;
	}

	/**
	 * @return the returnClass
	 */
	public TypeMirror getReturnClass() {
		return returnClass;
	}

	protected List<Pair<String, TypeMirror>> parameters;

	protected TypeMirror returnClass;

	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}

	/**
	 * Check if method contains a parameter with value as name
	 * 
	 * @param name
	 * 		parameter name to find
	 * @return
	 * 		true if parameter is found
	 */
	public boolean containsParameter(String name) {
		for (Pair<String, TypeMirror> item: parameters)
		{
			if (item.value0.equals(name))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if method contains a parameter with value as name
	 * 
	 * @param name
	 * 		parameter name to find
	 * @return
	 * 		TypeMirror associated
	 */
	public TypeMirror findParameter(String name) {
		for (Pair<String, TypeMirror> item: parameters)
		{
			if (item.value0.equals(name))
			{
				return item.value1;
			}
		}
		return null;
	}

	/**
	 * Check if method contains a parameter with value as name
	 * 
	 * @param name
	 * 		parameter name to find
	 * @return
	 * 		true if there is parameter with specified name 
	 */
	public boolean hasParameter(String name) {
		for (Pair<String, TypeMirror> item: parameters)
		{
			if (item.value0.equals(name))
			{
				return true;
			}
		}
		return false;
	}

}
