package com.abubusoft.kripton.processor.test04primary_key;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDeleteRaw;
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDao(Bean03.class)
public interface DaoBean03{

	@BindSelect(where="id=${id}")
	Bean03 selectOne(long id);
	
	@BindDeleteRaw(where="id=${id}")
	long deleteOne(long id);
	
}