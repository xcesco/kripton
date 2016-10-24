/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.example01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.processor.example01.Channel;
import com.abubusoft.kripton.processor.example01.DaoChannel;
import com.abubusoft.kripton.processor.example01.Dummy01DataSource;

import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Dummy01DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy01DataSource
 * @see BindDummy01DaoFactory
 * @see DaoChannel
 * @see BindDaoChannel
 * @see Channel
 */
public class BindDummy01DataSource extends AbstractDataSource implements BindDummy01DaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindDummy01DataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "dummy";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected BindDaoChannel daoChannel = new BindDaoChannel(this);

  protected BindDummy01DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindDaoChannel getDaoChannel() {
    return daoChannel;
  }

  /**
   * <p>executes a transaction. This method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>
   *
   * @param transaction transaction to execute
   */
  public synchronized void execute(Transaction transaction) {
    SQLiteDatabase connection=openDatabase();
    try {
      connection.beginTransaction();
      if (transaction!=null && transaction.onExecute(this)) {
        connection.setTransactionSuccessful();
      }
    } finally {
      connection.endTransaction();
      close();
    }
  }

  /**
   * instance
   */
  public static BindDummy01DataSource instance() {
    if (instance==null) {
      instance=new BindDummy01DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.example01.ChannelTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.example01.ChannelTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.example01.ChannelTable.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.example01.ChannelTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.example01.ChannelTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.example01.ChannelTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindDummy01DaoFactory> {
  }
}
