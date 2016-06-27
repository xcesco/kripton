package com.abubusoft.kripton.processor.kripton41;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;

@BindDao(Bean01.class)
public interface DaoBeanDeleteOK  {
	
	/*@BindSelect(value="count(*)>1", where="id=${id} and value=${value}")
	Boolean selectDistance(long id, double value);
	
	
	@BindInsert
	boolean insertDistance(long id, double value);
	*/
	/*
	@BindUpdate(where="id=${test}")
	boolean updateDistance(long id, Double value, long test);*/
	
	@BindDelete(where="id=${value}")
	boolean deleteDistance(double value);
	
	
}