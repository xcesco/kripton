package com.abubusoft.kripton.processor.sqlite.model;

import javax.lang.model.element.ExecutableElement;

import com.abubusoft.kripton.processor.core.ModelMethod;

public class SQLiteModelMethod extends ModelMethod implements SQLiteModelElement {
	@Override
	public void accept(SQLiteModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}
	
	public SQLiteModelMethod(ExecutableElement element) {
		super(element);		
	}
}
