package com.abubusoft.kriptonquickstart.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kriptonquickstart.model.Post;
import com.abubusoft.kriptonquickstart.model.User;

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
    User selectById(@BindSqlParam("value") long id);

}
