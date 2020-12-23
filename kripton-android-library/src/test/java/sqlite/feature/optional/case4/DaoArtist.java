package sqlite.feature.optional.case4;

import com.abubusoft.kripton.android.annotation.*;
import sqlite.feature.optional.model.Artist;
import sqlite.feature.optional.model.Simple;

import java.util.List;
import java.util.Optional;

@BindContentProviderPath(path = "artists")
@BindDao(Artist.class)
public interface DaoArtist {

  @BindSqlSelect
  List<Artist> selectAll();

  @BindSqlSelect(fields = {"id"}, where="id=${id}")
  Optional<Long> selectById(long id);

  @BindSqlSelect(fields = {"data"}, where="id=${id}")
  byte[] selectDataById(long id);

  @BindContentProviderEntry(path="title/:id")
  @BindSqlSelect(fields = {"title"}, where="id=${id}")
  Optional<String> selectTitleById(long id);

  @BindContentProviderEntry(path="title2/:id")
  @BindSqlSelect(jql = "SELECT title FROM Artist WHERE id=${id}")
  Optional<Simple> selectTitle2ById(long id);

  @BindSqlInsert
  int insert(Artist value);
}
