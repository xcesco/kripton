package com.abubusoft.kripton.processor.test04primary_key;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

public interface BindDummy01DaoFactory extends BindDaoFactory {
  BindDaoBean01 getDaoBean01(SQLiteDatabase database);
}
