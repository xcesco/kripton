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

import java.lang.String;

/**
 * <p>
 * Entity <code>Channel</code> is associated to table <code>channel</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.example01.Channel
 */
public class ChannelTable {
  /**
   * Costant represents name of table channel
   */
  public static final String TABLE_NAME = "channel";

  /**
   * <p>
   * DDL to create table channel
   * </p>
   *
   * <pre>CREATE TABLE channel (uid TEXT, owner_uid TEXT, update_time INTEGER, name TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE channel (uid TEXT, owner_uid TEXT, update_time INTEGER, name TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT);";

  /**
   * <p>
   * DDL to drop table channel
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS channel;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS channel;";

  /**
   * Entity's property <code>uid</code> is associated to table column <code>uid</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.example01.Channel#uid
   */
  public static final String COLUMN_UID = "uid";

  /**
   * Entity's property <code>ownerUid</code> is associated to table column <code>owner_uid</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.example01.Channel#ownerUid
   */
  public static final String COLUMN_OWNER_UID = "owner_uid";

  /**
   * Entity's property <code>updateTime</code> is associated to table column <code>update_time</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.example01.Channel#updateTime
   */
  public static final String COLUMN_UPDATE_TIME = "update_time";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.example01.Channel#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.example01.Channel#id
   */
  public static final String COLUMN_ID = "id";
}
