package com.abubusoft.kripton.processor.element;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;

public class KriptonTypeMirror implements TypeMirror {
	
	private TypeKind typeKind;

	public KriptonTypeMirror(TypeKind typeKind) {
		this.typeKind=typeKind;
	}

	@Override
	public TypeKind getKind() {
		return typeKind;
	}

	@Override
	public <R, P> R accept(TypeVisitor<R, P> v, P p) {
		return null;
	}

}
