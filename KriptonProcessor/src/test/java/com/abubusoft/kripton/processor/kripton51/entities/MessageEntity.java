package com.abubusoft.kripton.processor.kripton51.entities;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.kripton51.internal.MessageType;

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
    
    public MessageType type;
    

}
