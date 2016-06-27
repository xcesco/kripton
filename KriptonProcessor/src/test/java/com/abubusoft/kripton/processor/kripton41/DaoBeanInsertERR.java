package com.abubusoft.kripton.processor.kripton41;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;

@BindDao(Bean01.class)
public interface DaoBeanInsertERR  {
	/*
	@BindSelect(value="count(*)>1")
	Boolean selectDistance(long id, double value);
	*/
	
	@BindInsert
	boolean insertDistance(Long id, double value);
	
	/*
	@BindUpdate
	boolean updateDistance(long id, double value);
	
	@BindDelete
	boolean deleteDistance(long id, double value);
	*/
	
}