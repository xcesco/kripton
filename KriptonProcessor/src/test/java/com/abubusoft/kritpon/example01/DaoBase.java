package com.abubusoft.kritpon.example01;

import com.abubusoft.kripton.android.annotation.SQLDao;
import com.abubusoft.kripton.android.annotation.SQLDelete;

@SQLDao(ChannelMessage.class)
public interface DaoBase {
	
	@SQLDelete(where="id=${id}")
	int deleteById(long id);

}
