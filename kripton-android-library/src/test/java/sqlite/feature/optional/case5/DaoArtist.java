package sqlite.feature.optional.case5;

import com.abubusoft.kripton.android.annotation.*;
import sqlite.feature.optional.model.Artist;
import sqlite.feature.optional.model.Simple;

import java.util.List;
import java.util.Optional;

@BindContentProviderPath(path = "artists")
@BindDao(Artist.class)
public interface DaoArtist {

  @BindContentProviderEntry(path="title2/:id")
  @BindSqlSelect(jql = "SELECT title FROM Artist WHERE id=${id}")
  Optional<Simple> selectTitle2ById(long id);

  @BindSqlInsert
  int insert(Artist value);
}
