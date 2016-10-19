package com.abubusoft.kripton.processor.sqlite.model;

import java.lang.ref.WeakReference;

import javax.lang.model.element.ExecutableElement;

import com.abubusoft.kripton.processor.core.ModelMethod;

public class SQLiteModelMethod extends ModelMethod implements SQLiteModelElement {
	
	private WeakReference<SQLDaoDefinition> parent;

	/**
	 * @return the parent
	 */
	public SQLDaoDefinition getParent() {
		return parent.get();
	}
	
	public SQLiteModelMethod(SQLDaoDefinition parent, ExecutableElement element) {
		super(element);
		this.parent = new WeakReference<SQLDaoDefinition>(parent);
	}

	@Override
	public void accept(SQLiteModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}
		
}
