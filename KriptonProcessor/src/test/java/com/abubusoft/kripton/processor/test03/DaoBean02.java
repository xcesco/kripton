package com.abubusoft.kripton.processor.test03;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;

@BindDao(Bean01.class)
public interface DaoBean02
{

//	@BindSelect(where="value > ${startDate}")
//	public List<Bean01> selecList(long startDate);
//	
//	@BindSelect(where="value > ${startDate}")
//	public Cursor selectCursor(long startDate);
//	
//	@BindSelect(where="value > ${startDate}")
//	public Bean01 selectOne(long startDate);
	
//	@BindSelect(where="value > ${startDate}")
//	public void select(long startDate, ReadCursorListener listener);
	
	@BindSelect(where="value > ${startDate}")
	public void select(long startDate, ReadBeanListener<Bean01> listener);
	
}