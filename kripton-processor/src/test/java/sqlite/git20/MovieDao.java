package sqlite.git20;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Movie.class)
public interface MovieDao {
    @BindSqlSelect(jql="select 'title' as title, 1 as count")
    List<Count> findCountByTitle();

}