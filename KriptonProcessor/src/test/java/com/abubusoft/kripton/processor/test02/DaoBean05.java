package com.abubusoft.kripton.processor.test02;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSelect;

@BindDao(Bean05.class)
public interface DaoBean05 {

	@BindSelect(where="1=1")
	public List<Bean05> listAll();
	
	public void notWorking();
}
