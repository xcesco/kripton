package com.abubusoft.kripton.quickstart.model;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Todo {

    public long id;

    @BindColumn(foreignKey = User.class)
    public long userId;

    public String title;

    public boolean completed;
}
