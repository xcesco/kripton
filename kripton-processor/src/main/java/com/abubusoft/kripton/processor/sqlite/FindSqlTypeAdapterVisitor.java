package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

import com.abubusoft.kripton.processor.core.AnnotationAttributeType;

public class FindSqlTypeAdapterVisitor extends SimpleAnnotationValueVisitor7<Void, String> {

	boolean inAdapter = false;
	
	ArrayList<String> adapters=new ArrayList<String>();  

	public ArrayList<String> getAdapters() {
		return adapters;
	}


	@Override
	public Void visitAnnotation(AnnotationMirror a, String p) {
		if (AnnotationAttributeType.TYPE_ADAPTERS.getValue().equals(p)) {
			inAdapter = true;
		}

		for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : a.getElementValues().entrySet()) {
			String key = entry.getKey().getSimpleName().toString();
			entry.getValue().accept(this, key);
		}

		if (AnnotationAttributeType.TYPE_ADAPTERS.getValue().equals(p)) {
			inAdapter = false;
		}

		return null;
	}

	@Override
	public Void visitType(TypeMirror t, String p) {
		//System.out.printf(">> %s classValue: %s\n", p, t.toString());
			
		if (inAdapter && AnnotationAttributeType.ADAPTER.getValue().equals(p)) {
			// add value
			adapters.add(t.toString());			
		}
		
		return null;
	}

	@Override
	public Void visitArray(List<? extends AnnotationValue> vals, String p) {
		for (AnnotationValue val : vals) {
			val.accept(this, p);
		}
		return null;
	}
}