package com.abubusoft.kripton.quickstart.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.quickstart.model.User;

import java.util.List;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDao(User.class)
public interface UserDao {

    @BindSqlInsert(includePrimaryKey = true)
    void insert(User bean);

    @BindSqlSelect(orderBy = "username asc")
    List<User> selectAll();

    @BindSqlSelect(where="id = ${value}")
    User selectById(@BindSqlParam("value") long id);

    @BindSqlSelect
    List<User> selectDynamic(@BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

    @BindSqlSelect
    List<User> sortedFind(@BindSqlDynamicOrderBy String orderBy);

}
