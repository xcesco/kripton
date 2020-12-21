/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.kripton51.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.kripton51.entities.MessageEntity;


/**
 * Created by xcesco on 27/09/2016.
 */

@BindDao(MessageEntity.class)
public interface DaoMessage {

    /**
     * Select by channel.
     *
     * @param channelId the channel id
     * @return the list
     */
    @BindSqlSelect(where =" channelId = ${channelId}")
    List<MessageEntity> selectByChannel(long channelId);

    /**
     * Update by id.
     *
     * @param bean the bean
     * @return true, if successful
     */
    @BindSqlUpdate(where=" id = ${bean.id}")
    boolean updateById(MessageEntity bean);

    /**
     * Insert.
     *
     * @param bean the bean
     */
    @BindSqlInsert
    void insert(MessageEntity bean);

    /**
     * Select by uid.
     *
     * @param uid the uid
     * @return the message entity
     */
    @BindSqlSelect(where =" uid = ${uid}")
    MessageEntity selectByUid(String uid);
}
