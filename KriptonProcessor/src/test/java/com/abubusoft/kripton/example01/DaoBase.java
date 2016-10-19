package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;


@BindDao(ChannelMessage.class)
public interface DaoBase {
	
	@BindDelete(where="id=${id}")
	int deleteById(long id);

}
