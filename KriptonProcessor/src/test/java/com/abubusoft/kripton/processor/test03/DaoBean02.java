package com.abubusoft.kripton.processor.test03;

import java.util.List;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;

@BindDao(Bean01.class)
public interface DaoBean02
{

	@BindSelect(where="value > ${startDate}")
	public List<Bean01> selecList(long startDate);
	
	@BindSelect(where="value > ${startDate}")
	public Cursor selectCursor(long startDate);
	
	@BindSelect(where="value > ${startDate}")
	public Bean01 selectOne(long startDate);
	
	@BindSelect(where="value > ${startDate}")
	public void select(long startDate, ReadCursorListener listener);
	
}