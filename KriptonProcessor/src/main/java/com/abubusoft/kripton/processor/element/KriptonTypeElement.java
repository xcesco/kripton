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
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.common.StringUtils;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.TypeSpec;

public class KriptonTypeElement implements TypeElement {

	private String packageName;
	private TypeSpec typeSpec;

	public KriptonTypeElement(String packageName, TypeSpec typeSpec) {
		this.packageName = packageName;
		this.typeSpec = typeSpec;
	}

	@Override
	public TypeMirror asType() {
		return new KriptonTypeMirror(this.typeSpec.name);
	}

	@Override
	public ElementKind getKind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends AnnotationMirror> getAnnotationMirrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
		for (AnnotationSpec item : typeSpec.annotations) {
			if (item.type.toString().equals(annotationType.getCanonicalName())) {
				return Class.forName(item.type.toString());
			}
		}

		return null;
	}

	@Override
	public Set<Modifier> getModifiers() {
		return typeSpec.modifiers;
	}

	@Override
	public <R, P> R accept(ElementVisitor<R, P> v, P p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Element> getEnclosedElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NestingKind getNestingKind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Name getQualifiedName() {
		return new KriptonName(StringUtils.ifNotEmptyPrepend(packageName, ".") + typeSpec.name);
	}

	@Override
	public Name getSimpleName() {
		return new KriptonName(typeSpec.name);
	}

	@Override
	public TypeMirror getSuperclass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends TypeMirror> getInterfaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends TypeParameterElement> getTypeParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getEnclosingElement() {
		// TODO Auto-generated method stub
		return null;
	}

}
