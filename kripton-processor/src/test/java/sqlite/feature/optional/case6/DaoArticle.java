package sqlite.feature.optional.case6;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import sqlite.feature.optional.model.Article;

@BindDao(Article.class)
public interface DaoArticle {

  @BindSqlInsert
  int insert(Article value);
}
