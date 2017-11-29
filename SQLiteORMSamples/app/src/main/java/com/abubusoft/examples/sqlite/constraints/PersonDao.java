package com.abubusoft.examples.sqlite.constraints;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

@BindDao(Person.class)
public interface PersonDao {

    @BindSqlSelect(jql="select person.* from person inner join city on city.id=person.cityId where person.cityId=${cityId}")
    List<Person> listForCity(long cityId);


    @BindSqlInsert
    void insert(Person bean);
}
