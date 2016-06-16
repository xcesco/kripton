package com.abubusoft.kripton.processor.test05firt_aid;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

public interface BindFirstAidDaoFactory extends BindDaoFactory {
  BindFirstAidDao getFirstAidDao(SQLiteDatabase database);
}
