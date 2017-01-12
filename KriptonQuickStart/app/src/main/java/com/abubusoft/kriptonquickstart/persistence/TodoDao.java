package com.abubusoft.kriptonquickstart.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kriptonquickstart.model.Post;
import com.abubusoft.kriptonquickstart.model.Todo;

import java.util.List;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDao(Todo.class)
public interface TodoDao {
    @BindSqlInsert
    void insert(Todo bean);

    @BindSqlSelect(where="userId = ${value}")
    List<Todo> selectByUserId(@BindSqlParam("value") long userId);

    @BindSqlSelect(where="id = ${value}")
    Todo selectOneByUserId(@BindSqlParam("value") long userId);

}
