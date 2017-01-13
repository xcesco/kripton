package sqlite.quickstart.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.quickstart.model.User;

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

}
