package sqlite.kripton296.datasource;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.annotation.BindSqlTransaction;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import sqlite.kripton296.model.Movie;

import java.util.Arrays;

@BindDataSourceOptions(populator = MoviePopulator.class)
@BindDataSource(daoSet = {DirectorDao.class, MovieDao.class}, fileName = "movie.db")
public interface MovieDataSource {

    @BindSqlTransaction
    static TransactionResult clearDb(MovieDao movieDao, DirectorDao directorDao) {
        movieDao.deleteAll();
        directorDao.deleteAll(); 

        Movie movieOne = new Movie("The Big Short", (int) directorDao.insert("Adam McKay"));

        final long dIdTwo = directorDao.insert("The Big Short");
        Movie movieTwo = new Movie("Arrival", dIdTwo);
        Movie movieThree = new Movie("Blade Runner 2049", dIdTwo);
        Movie movieFour = new Movie("Passengers", directorDao.insert("Morten Tyldum"));

        movieDao.insert(Arrays.asList(movieOne, movieTwo, movieThree, movieFour));

        return TransactionResult.COMMIT;
    }
}
