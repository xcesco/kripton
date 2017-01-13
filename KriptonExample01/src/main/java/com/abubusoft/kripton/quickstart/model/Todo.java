package com.abubusoft.kripton.quickstart.model;

import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by xcesco on 12/01/2017.
 */
@BindType
public class Todo {

    public long id;

    public long userId;

    public String title;

    public boolean completed;
}
