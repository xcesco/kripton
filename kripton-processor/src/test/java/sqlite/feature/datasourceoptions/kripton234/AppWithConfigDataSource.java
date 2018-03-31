package sqlite.feature.datasourceoptions.kripton234;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;

import sqlite.feature.datasourceoptions.PersonPopulator;

@BindDataSourceOptions(logEnabled=false, populator= PersonPopulator.class)
@BindDataSource(daoSet = { DaoPerson.class }, fileName = "app.db")
public interface AppWithConfigDataSource {

}
