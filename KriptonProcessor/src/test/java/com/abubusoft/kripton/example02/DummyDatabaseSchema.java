package com.abubusoft.kripton.example02;

import com.abubusoft.kripton.android.annotation.BindDatabaseSchema;


@BindDatabaseSchema(value={ChannelMessage.class}, fileName = "pippo" , version=1)
public interface DummyDatabaseSchema {

}
