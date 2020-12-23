package sqlite.feature.optional.case2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import sqlite.feature.optional.model.Artist;

import java.util.List;
import java.util.Optional;

@BindDao(Artist.class)
public interface DaoArtist {

  @BindSqlSelect
  List<Artist> selectAll();

  @BindSqlSelect(fields = {"id"}, where="id=${id}")
  Optional<Long> selectById(long id);

  @BindSqlSelect(fields = {"title"}, where="id=${id}")
  Optional<String> selectTitleById(long id);

  @BindSqlSelect(fields = {"data"}, where="id=${id}")
  Optional<byte[]> selectDataById(long id);

  @BindSqlInsert
  int insert(Artist value);
}
