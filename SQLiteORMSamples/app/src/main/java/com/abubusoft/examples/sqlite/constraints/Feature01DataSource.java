package com.abubusoft.examples.sqlite.constraints;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 29/11/2017.
 */
@BindDataSource(daoSet = {CityDao.class, PersonDao.class}, fileName = "feature01.db")
public interface Feature01DataSource {
}
