package com.abubusoft.whisper.client.entities;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.whisper.model.User;

/**
 * Created by xcesco on 27/09/2016.
 */

@BindType
@BindTable("user")
public class UserEntity extends User {
    public long id;
}
