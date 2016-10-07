package com.abubusoft.kripton.tutorial;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;

@BindDao(User.class)
public interface DaoUser {

	@BindInsert
	public long insert(User bean);
	
	@BindUpdate
	public long update(User bean);
	
	@BindDelete(where="id=${id}")
	public long delete(long id);
	
	@BindSelect(where="id=${id}")
	public User selectById(long id);
	
	@BindSelect(where =" name=${name}")
	public List<User> selectById(String name);
	
}
