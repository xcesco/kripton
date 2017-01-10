package com.abubusoft.kriptonquickstart.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kriptonquickstart.persistence.UserDao;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDataSource(dao={UserDao.class, PostDao.class}, fileName = "quickstart.db")
public interface QuickStartDataSource {
}
