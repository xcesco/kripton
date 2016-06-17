package com.abubusoft.kripton.processor.test04primary_key;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

public interface BindDummy02DaoFactory extends BindDaoFactory {
  BindDaoBean02 getDaoBean02(SQLiteDatabase database);
}
