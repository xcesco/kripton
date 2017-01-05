package com.abubusoft.kriptonquickstart;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDataSource(dao={UserDao.class}, fileName = "quickstart.db")
public interface QuickStartDataSource {
}
