package sqlite.feature.optional.case5;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import sqlite.feature.optional.model.Article;

import java.util.Optional;

@BindDao(Article.class)
public interface DaoArticle {

  @BindSqlInsert
  int insert(Article value);
}
