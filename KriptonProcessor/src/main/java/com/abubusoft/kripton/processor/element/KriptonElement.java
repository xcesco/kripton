package com.abubusoft.kripton.processor.element;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;

public class KriptonElement implements Element {

	private KriptonTypeMirror typeMirror;
	private ElementKind kind;

	public KriptonElement(KriptonTypeMirror typeMirror, ElementKind kind) {
		this.typeMirror=typeMirror;
		this.kind=kind;
	}
	
	@Override
	public TypeMirror asType() {
		return typeMirror;
	}

	@Override
	public ElementKind getKind() {
		return kind;
	}

	@Override
	public List<? extends AnnotationMirror> getAnnotationMirrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Modifier> getModifiers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Name getSimpleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getEnclosingElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Element> getEnclosedElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R, P> R accept(ElementVisitor<R, P> v, P p) {
		// TODO Auto-generated method stub
		return null;
	}

}
