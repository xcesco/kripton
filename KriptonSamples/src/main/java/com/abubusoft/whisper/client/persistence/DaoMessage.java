package com.abubusoft.whisper.client.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.whisper.client.entities.ChannelEntity;
import com.abubusoft.whisper.client.entities.MessageEntity;
import com.abubusoft.whisper.model.Message;

import java.util.List;

/**
 * Created by xcesco on 27/09/2016.
 */

@BindDao(MessageEntity.class)
public interface DaoMessage {

    @BindSelect(where =" channelId = ${channelId}")
    List<MessageEntity> selectByChannel(long channelId);

    @BindUpdate(where=" id = ${bean.id}")
    boolean updateById(MessageEntity bean);

    @BindInsert
    void insert(MessageEntity bean);

    @BindSelect(where =" uid = ${uid}")
    MessageEntity selectByUid(String uid);
}
