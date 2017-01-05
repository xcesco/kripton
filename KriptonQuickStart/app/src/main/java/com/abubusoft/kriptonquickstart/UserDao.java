package com.abubusoft.kriptonquickstart;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDao(User.class)
public interface UserDao {

    @BindSqlInsert
    void insert(User bean);

    @BindSqlSelect
    List<User> selectAll();

    @BindSqlSelect(where="id = ${value}")
    User isPresent(@BindSqlParam("value") long id);
}
