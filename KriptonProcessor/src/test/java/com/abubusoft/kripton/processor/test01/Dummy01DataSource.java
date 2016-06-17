package com.abubusoft.kripton.processor.test01;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.example01.DaoChannelMessage;


@BindDataSource(value=DaoChannelMessage.class, fileName = "dummy" , version=1)
public interface Dummy01DataSource {

}
