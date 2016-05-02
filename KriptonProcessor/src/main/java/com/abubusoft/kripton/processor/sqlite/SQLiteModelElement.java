package com.abubusoft.kripton.processor.sqlite;

public interface SQLiteModelElement  {

	void accept(SQLiteModelElementVisitor visitor) throws Exception;
}
