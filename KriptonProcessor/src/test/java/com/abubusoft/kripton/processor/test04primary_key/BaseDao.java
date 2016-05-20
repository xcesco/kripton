package com.abubusoft.kripton.processor.test04primary_key;

import com.abubusoft.kripton.android.annotation.BindDeleteRaw;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdateRaw;

public interface BaseDao<E> {

	@BindSelect(where="id=${id}")
	E selectOne(long id);
	
	@BindUpdateRaw(where="id=${id}")
	long updateOne(String text, long id);
}
