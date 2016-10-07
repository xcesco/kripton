package com.abubusoft.kripton.tutorial;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName = "security.db" , value={DaoUser.class})
public interface SecurityDataSource {

}
