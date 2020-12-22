package sqlite.feature.optional.case2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import sqlite.feature.optional.model.Article;

import java.util.List;
import java.util.Optional;

@BindDao(Article.class)
public interface DaoArticle {



  @BindSqlSelect(fields = {"id"}, where="id=${id}")
  Optional<Long> selectById(long id);

  @BindSqlSelect(fields = {"title"}, where="id=${id}")
  Optional<String> selectTitleById(long id);

  @BindSqlInsert
  int insert(Article value);
}
