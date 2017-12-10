package com.abubusoft.kripton.samples.sqlite.sample02sql;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 29/11/2017.
 */
@BindDataSource(daoSet = {PersonDao.class}, fileName = "feature01.db", )
public interface SampleDataSource {
}
