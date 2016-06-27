package com.abubusoft.kripton.processor.kripton41;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDao(Bean01.class)
public interface DaoBeanSelectOK  {
	
	@BindSelect(value="count(*)>1", where="id=${id} and value=${value}")
	Boolean selectDistance(long id, double value);
	
	/*
	@BindInsert
	boolean insertDistance(long id, double value);
	
	@BindUpdate
	boolean updateDistance(long id, double value);
	
	@BindDelete
	boolean deleteDistance(long id, double value);
	*/
	
}