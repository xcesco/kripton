package sqlite.git20.mutable;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Movie.class)
public interface MovieDao {
    @BindSqlSelect(jql="select 'title' as title, count(*) as count")
    List<Count> findCountByTitle();

}