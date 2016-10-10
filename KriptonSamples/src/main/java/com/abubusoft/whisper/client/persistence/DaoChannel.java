package com.abubusoft.whisper.client.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.whisper.client.entities.ChannelEntity;
import com.abubusoft.whisper.client.entities.MessageEntity;
import com.abubusoft.whisper.model.Channel;

import java.util.List;

/**
 * Created by xcesco on 27/09/2016.
 */

@BindDao(ChannelEntity.class)
public interface DaoChannel  {

    @BindUpdate(where=" id = ${bean.id}")
    boolean updateById(ChannelEntity bean);

    @BindSelect(where=" uid = ${uid}")
    ChannelEntity selectByUid(String uid);

    @BindInsert
    void insert(ChannelEntity bean);

    @BindSelect
    List<ChannelEntity> selectAll();
}
