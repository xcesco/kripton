package com.abubusoft.whisper.client.entities;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.whisper.client.services.ServiceAuthorization;
import com.abubusoft.whisper.model.Message;

/**
 * Created by xcesco on 27/09/2016.
 */

@BindType
@BindTable("message")
public class MessageEntity extends Message {

    public long id;

    public long channelId;

    public OwnerType ownerType;

    public static MessageEntity convert(long channelId, Message item) {

        //BindDaoMessage daoMessage=null;
        //daoMessage.insert(new MessageEntity());
        MessageEntity result=new MessageEntity();

        result.setUid(item.getUid());
        result.channelId=channelId;
        result.setChannelUid(item.getChannelUid());
        result.setFaceUid(item.getFaceUid());
        result.setOwnerUid(item.getOwnerUid());
        result.setText(item.getText());
        result.setType(item.getType());
        result.setUpdateTime(item.getUpdateTime());
/*
        if (item.getOwnerUid()==null)
        {
            result.ownerType= OwnerType.SYSTEM_MESSAGE;
        } else if (item.getOwnerUid().equals(ServiceAuthorization.instance().getUserUid())) {
            result.ownerType= OwnerType.OWN_MESSAGE;
        } else {
            result.ownerType= OwnerType.OTHER_MESSAGE;
        }
        */

        return result;
    }

    public boolean isOwnMessage()
    {
        return ServiceAuthorization.instance().getUserUid().equals(this.getOwnerUid());
    }

}
