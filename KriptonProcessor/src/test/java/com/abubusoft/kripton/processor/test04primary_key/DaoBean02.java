package com.abubusoft.kripton.processor.test04primary_key;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDeleteRaw;
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDao(Bean02.class)
public interface DaoBean02 {

	@BindSelect(where="id=${id}")
	Bean02 selectOne(long id);
	
	@BindDeleteRaw(where="id=${id}")
	long deleteOne(long id);
	
}