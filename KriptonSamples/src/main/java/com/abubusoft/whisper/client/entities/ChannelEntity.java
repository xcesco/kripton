package com.abubusoft.whisper.client.entities;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.whisper.model.Channel;

/**
 * Created by xcesco on 27/09/2016.
 */

@BindType
@BindTable("channel")
public class ChannelEntity extends Channel {

    public long id;

    public static ChannelEntity convert(Channel input)
    {
        ChannelEntity result=new ChannelEntity();

        result.setUid(input.getUid());
        result.setImageSize(input.getImageSize());
        result.setImageType(input.getImageType());
        result.setName(input.getName());

        return result;
    }
}
