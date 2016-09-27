package com.abubusoft.kripton.processor.kripton48.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.processor.kripton48.entities.Bean02;

@BindDao(Bean02.class)
public interface DaoBean02 {

	@BindSelect(where="id=${id}")
	Bean02 selectOne(long id);
	
	@BindDelete(where="id=${id}")
	long deleteOne(long id);
	
}