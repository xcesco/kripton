package com.abubusoft.kripton.processor.sqlite.model;

public interface SQLiteModelElement  {

	void accept(SQLiteModelElementVisitor visitor) throws Exception;
}
