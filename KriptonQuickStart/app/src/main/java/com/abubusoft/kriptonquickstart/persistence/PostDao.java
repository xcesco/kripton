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
@BindDao(Post.class)
public interface PostDao {
    @BindSqlInsert
    void insert(Post bean);

    @BindSqlSelect
    List<Post> selectAll();

    @BindSqlSelect(where="id = ${value}")
    Post selectById(@BindSqlParam("value") long id);

}
