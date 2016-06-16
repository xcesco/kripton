package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(value={ChannelMessage.class, Channel.class}, fileName = "pippo" , version=2)
public interface DummyDatabaseSchema {

}
