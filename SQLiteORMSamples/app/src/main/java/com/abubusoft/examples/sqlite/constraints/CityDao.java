package com.abubusoft.examples.sqlite.constraints;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

@BindDao(City.class)
public interface CityDao {

    @BindSqlSelect
    List<City> selectAll();

    @BindSqlInsert
    void insert(City bean);
}
