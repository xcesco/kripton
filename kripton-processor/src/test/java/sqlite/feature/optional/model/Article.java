package sqlite.feature.optional.model;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType(name = "articles")
public class Article {
  private final long id;

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  private final String title;

  public Article(long id, String title) {
    this.id = id;
    this.title = title;
  }
}
