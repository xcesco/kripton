package com.abubusoft.kripton.processor.test04primary_key;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

public interface BindDummy05DaoFactory extends BindDaoFactory {
  BindDaoBean05 getDaoBean05(SQLiteDatabase database);
}
