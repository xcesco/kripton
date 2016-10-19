package com.abubusoft.kripton.example02;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSelect;

/**
 * Created by 908099 on 10/05/2016.
 */
@BindDao(ChannelMessage.class)
public interface DaoChannelMessage {

    @BindSelect(where="1=1")
    List<ChannelMessage> selectAll();
}
