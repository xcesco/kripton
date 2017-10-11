package com.abubusoft.kripton.processor.element;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;

public class KriptonTypeMirror implements TypeMirror {
	
	

	public KriptonTypeMirror(String name) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TypeKind getKind() {
		return null;
	}

	@Override
	public <R, P> R accept(TypeVisitor<R, P> v, P p) {		
		return null;
	}

}
