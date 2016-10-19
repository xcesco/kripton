package com.abubusoft.kripton.processor.kripton51.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.processor.kripton51.entities.MessageEntity;

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
