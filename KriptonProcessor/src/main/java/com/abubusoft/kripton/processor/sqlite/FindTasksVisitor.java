package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;

public class FindTasksVisitor extends SimpleAnnotationValueVisitor7<Void, String> {

	boolean inTasks = false;

	Pair<Integer, String> currentValue;
	
	ArrayList<Pair<Integer, String>> tasks=new ArrayList<Pair<Integer, String>>();  

	public ArrayList<Pair<Integer, String>> getTasks() {
		return tasks;
	}

	@Override
	public Void visitInt(int i, String p) {
		//System.out.printf(">> %s intValue: %d\n", p, i);

		if (inTasks && AnnotationAttributeType.VERSION.getValue().equals(p)) {
			// add value
			currentValue = (currentValue == null) ? new Pair<Integer, String>() : currentValue;			
			currentValue.value0=i;
			
			if (currentValue.value0!=null && StringUtils.hasText(currentValue.value1)) {
				tasks.add(currentValue);
				currentValue=null;
			}
		}

		return null;
	}

	@Override
	public Void visitString(String s, String p) {
		//System.out.printf(">> %s stringValue: %s\n", p, s);
						
		return null;
	}

	@Override
	public Void visitEnumConstant(VariableElement c, String p) {
		//System.out.printf(">> %s enumValue: %s\n", p, c.getSimpleName());
		return null;
	}

	@Override
	public Void visitAnnotation(AnnotationMirror a, String p) {
		if (AnnotationAttributeType.UPDATE_TASKS.getValue().equals(p)) {
			inTasks = true;
		}

		for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : a.getElementValues().entrySet()) {
			String key = entry.getKey().getSimpleName().toString();
			entry.getValue().accept(this, key);
		}

		if (AnnotationAttributeType.UPDATE_TASKS.getValue().equals(p)) {
			inTasks = false;
		}

		return null;
	}

	@Override
	public Void visitType(TypeMirror t, String p) {
		//System.out.printf(">> %s classValue: %s\n", p, t.toString());
			
		if (inTasks && AnnotationAttributeType.TASK.getValue().equals(p)) {
			// add value
			currentValue = (currentValue == null) ? new Pair<Integer, String>() : currentValue;
			
			currentValue.value1=t.toString();
			
			if (currentValue.value0!=null && StringUtils.hasText(currentValue.value1)) {
				tasks.add(currentValue);
				currentValue=null;
			}
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