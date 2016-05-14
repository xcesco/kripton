package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.android.annotation.BindDatabase;

@BindDatabase(value={ChannelMessage.class, Channel.class}, fileName = "pippo" , version=2)
public interface DummyDatabaseSchema {

}
