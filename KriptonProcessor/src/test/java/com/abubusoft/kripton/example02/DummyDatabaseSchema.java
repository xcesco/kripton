package com.abubusoft.kripton.example02;

import com.abubusoft.kripton.android.annotation.BindDatabase;


@BindDatabase(value={ChannelMessage.class}, fileName = "pippo" , version=1)
public interface DummyDatabaseSchema {

}
