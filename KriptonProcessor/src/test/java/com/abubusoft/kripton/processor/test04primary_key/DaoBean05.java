package com.abubusoft.kripton.processor.test04primary_key;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindDeleteRaw;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.annotation.BindUpdateRaw;

@BindDao(Bean05.class)
public interface DaoBean05 {

	@BindSelect(where="pk=${id}")
	Bean05 selectOne(long id);
	
	@BindDeleteRaw(where="pk=${id}")
	long deleteOne(long id);
	
	@BindDelete(where="pk=${bean.pk} and pk=${a}")
	long deleteOne(Bean05 bean, long a);
	
	@BindUpdate(where="pk=${bean.pk} and pk=${a}")
	long updateOne(Bean05 bean, long a);
	
	@BindSelect(value="content",where="pk=${id}")
	byte[] getOne(long id);
	
	@BindUpdateRaw(where="pk=${uid}")
	long updateOne(String text, long uid );
	
}