package sqlite.feature.pkstring.rx;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.android.sqlite.PagedResultImpl;

import android.arch.lifecycle.LiveData;

@BindContentProviderPath(path="user")
@BindDao(User.class)
public interface UserDao {

    /**
     * Get the user from the table. Since for simplicity we only have one user in the database,
     * this query gets all users from the table, but limits the result to just the 1st user.
     *
     * @return the user from the table
     */
    @BindSqlSelect(where="id=:id", pageSize = 1)
    User getUser(String id);
    
    @BindSqlSelect(pageSize=20)
    LiveData<PagedResultImpl<User>> selectPaged(); 

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @BindContentProviderEntry(path="insert1")
    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
    void insertUser(User user);
    
    @BindContentProviderEntry(path="insert2")
    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
    void insertUser(String id);

    /**
     * Delete all users.
     */
    @BindSqlDelete
    void deleteAllUsers();
}
