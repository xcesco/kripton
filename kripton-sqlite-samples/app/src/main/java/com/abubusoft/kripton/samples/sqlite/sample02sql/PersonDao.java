package com.abubusoft.kripton.samples.sqlite.sample02sql;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import java.util.List;

@BindDao(Person.class)
public interface PersonDao {

    @BindSqlSelect(where="id=${id}")
    Person selectById(long id);

    @BindSqlSelect(orderBy = "name")
    List<Person> selectAll();

    @BindSqlInsert
    long insert(Person bean);

    @BindSqlInsert
    long insertOnlyName(String name);

    @BindSqlUpdate(where="id=${bean.id}")
    int update(Person bean);

    @BindSqlUpdate(where="surname = ${param1}")
    long insertOnlyName(String name, String param1);

    @BindSqlDelete(where="id=${bean.id}")
    int delete(Person bean);

    @BindSqlDelete
    void deleteAll();

}
