package com.abubusoft.kripton.processor.sqlite;

import javax.annotation.processing.Filer;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class AbstractBuilder {
	
	protected Elements elementUtils;
	protected Filer filer;
	protected Builder builder;
	protected SQLiteDatabaseSchema model;
	
	public AbstractBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		this.model=model;
		this.elementUtils = elementUtils;
		this.filer = filer;
		
	}
		
}
