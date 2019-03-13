package sqlite.git20;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = {MovieDao.class}, fileName = "movie.db")
public interface MovieDataSource {

  
}
