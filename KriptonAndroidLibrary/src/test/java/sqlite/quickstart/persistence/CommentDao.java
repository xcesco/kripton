package sqlite.quickstart.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.quickstart.model.Comment;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDao(Comment.class)
public interface CommentDao {
    @BindSqlInsert(includePrimaryKey = true)
    void insert(Comment bean);

    @BindSqlSelect(where="postId = ${value}")
    List<Comment> selectByPostId(@BindSqlParam("value") long postId);

    @BindSqlSelect(where="id = ${value}")
    Comment selectOneByPostId(@BindSqlParam("value") long postId);

}
