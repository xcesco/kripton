package com.abubusoft.kripton.processor.kripton38;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;

@BindDao(Bean06.class)
public interface DaoBean06 {

	@BindDelete(where="pk=${idM}")
	long deleteOne();
	
}