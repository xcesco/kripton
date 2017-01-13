package com.abubusoft.kripton.quickstart.persistence;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

/**
 * Created by xcesco on 07/01/2017.
 */

public interface AbstractDao<E> {

    @BindSqlInsert
    void insert(E bean);

    @BindSqlSelect
    List<E> selectAll();

    @BindSqlSelect(where="id = ${value}")
    E selectById(@BindSqlParam("value") long id);
}
