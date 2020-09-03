package sqlite.kripton296.datasource;

import androidx.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import sqlite.kripton296.model.Director;

import java.util.List;

@BindDao(Director.class)
public interface DirectorDao {
    @BindSqlSelect(where = "did = :id LIMIT 1")
    Director findDirectorById(long id);

    @BindSqlSelect(where = "fullName = :fullName LIMIT 1")
    Director findDirectorByName(String fullName);

    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    long insert(String fullName);

    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    void insert(List<Director> directors);

    @BindSqlUpdate(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    void update(Director director);

    @BindSqlDelete()
    void deleteAll();

    @BindSqlSelect(orderBy = "fullName ASC")
    LiveData<List<Director>> getAllDirectors();
}