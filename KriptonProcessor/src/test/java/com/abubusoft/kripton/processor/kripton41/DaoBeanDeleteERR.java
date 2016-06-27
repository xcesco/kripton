package com.abubusoft.kripton.processor.kripton41;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;

@BindDao(Bean01.class)
public interface DaoBeanDeleteERR  {
	/*
	@BindSelect(value="count(*)>1")
	Boolean selectDistance(long id, double value);
	*/
	/*
	@BindInsert
	boolean insertDistance(Long id, double value);
	
	
	@BindUpdate
	boolean updateDistance(long id, Double value, long test);*/
	
	@BindDelete
	boolean deleteDistance(long id, double value);
	
	
}