package com.abubusoft.kripton.example01;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

public interface BindDummy01DaoFactory extends BindDaoFactory {
  BindDaoChannel getDaoChannel(SQLiteDatabase database);
}
