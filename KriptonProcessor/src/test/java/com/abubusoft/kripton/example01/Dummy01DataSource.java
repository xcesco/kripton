package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.android.annotation.BindDataSource;


@BindDataSource(value={DaoChannel.class}, fileName = "dummy" , version=1)
public interface Dummy01DataSource {

}
