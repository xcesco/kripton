package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;

public class MethodWithoutSupportedAnnotationException extends SQLiteProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public MethodWithoutSupportedAnnotationException(SQLDaoDefinition daoDefinition, ModelMethod method) {
		super("Method '" + method.getName() + "' of DAO '" + daoDefinition.getName() + "' is not marked with any valid annotation (" +BindInsert.class.getSimpleName() + ", "
				+ BindUpdate.class.getSimpleName() + ", " + BindDelete.class.getSimpleName() + ", " + BindSelect.class.getSimpleName() + ", "
				+ ")");
	}
}
