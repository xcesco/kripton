package com.abubusoft.kripton.samples.sqlite.sample01constraints;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

/**
 * Created by xcesco on 29/11/2017.
 */
@BindTable
public class Person {
    public long id;

    @BindColumn(foreignKey = City.class, nullable = false)
    public long cityId;

    public String name;

    public String surname;
}
