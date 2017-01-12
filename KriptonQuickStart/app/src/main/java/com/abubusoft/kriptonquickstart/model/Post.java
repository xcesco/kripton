package com.abubusoft.kriptonquickstart.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Post {

    @BindColumn(foreignKey = User.class )
    public long userId;

    public long id;

    public String title;

    public String body;
}
