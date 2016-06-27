package com.abubusoft.kripton.processor.kripton40;

import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;

public interface BaseDao<E> {

	@BindSelect(where="id=${id}")
	E selectOne(long id);
	
	@BindUpdate(where="id=${id}")
	long updateOne(String text, long id);
}
