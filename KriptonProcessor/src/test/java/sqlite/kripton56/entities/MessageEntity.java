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
package sqlite.kripton56.entities;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

import sqlite.kripton56.internal.MessageType;

/**
 * Created by xcesco on 27/09/2016.
 */

@BindType
@BindTable("message")
public class MessageEntity {

    public long id;

    public long channelId;

    public OwnerType ownerType;
    
    public String faceUid;
    public String text;
    public String ownerUid;
    public String channelUid;
    public long updateTime;
    
    @BindColumn(enabled=false)
    public MessageType type;
    

}
