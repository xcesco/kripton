package com.abubusoft.kritpon.example01;

import com.abubusoft.kripton.android.annotation.SQLDatabaseSchema;

@SQLDatabaseSchema(value={ChannelMessage.class}, fileName = "pippo" , version=2)
public interface DummyDatabaseSchema {

}
