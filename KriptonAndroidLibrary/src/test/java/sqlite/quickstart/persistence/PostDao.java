package sqlite.quickstart.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.quickstart.model.Post;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDao(Post.class)
public interface PostDao {
    @BindSqlInsert(includePrimaryKey = true)
    void insert(Post bean);

    @BindSqlSelect(where="userId = ${value}")
    List<Post> selectByUserId(@BindSqlParam("value") long userId);

    @BindSqlSelect(where="id = ${value}")
    Post selectOneByUserId(@BindSqlParam("value") long userId);

}
