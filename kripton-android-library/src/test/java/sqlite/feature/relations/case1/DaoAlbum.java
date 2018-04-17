package sqlite.feature.relations.case1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Album.class)
public interface DaoAlbum extends DaoBase<Album> {

	@BindSqlSelect
	List<Album> selectAlbums();
}
