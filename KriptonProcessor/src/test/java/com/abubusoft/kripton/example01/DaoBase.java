package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.android.annotation.BindDaoDefinition;
import com.abubusoft.kripton.android.annotation.BindDelete;


@BindDaoDefinition(ChannelMessage.class)
public interface DaoBase {
	
	@BindDelete(where="id=${id}")
	int deleteById(long id);

}
