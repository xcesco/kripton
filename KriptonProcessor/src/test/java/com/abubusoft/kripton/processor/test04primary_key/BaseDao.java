package com.abubusoft.kripton.processor.test04primary_key;

import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;

public interface BaseDao<E> {

	@BindSelect(where="id=${id}")
	E selectOne(long id);
	
	@BindUpdate(where="id=${id}")
	long updateOne(String text, long id);
}
