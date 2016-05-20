package com.abubusoft.kripton.processor.test03;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDao(Bean01.class)
public interface DaoBean01 {

	@BindSelect(where="1=1")
	public List<Bean01> listAll();
	
}