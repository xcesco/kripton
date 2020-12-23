package sqlite.feature.optional.case1;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import sqlite.feature.optional.model.Article;

import java.util.List;
import java.util.Optional;

@BindDao(Article.class)
public interface DaoArticle {

  @BindSqlSelect
  List<Article> selectAll();

  @BindSqlSelect(where="id=${id}")
  Optional<Article> selectById(long id);

  @BindSqlInsert
  int insert(Article value);
}
