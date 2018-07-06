package sqlite.feature.pkstring.relations.err1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Album.class)
public interface DaoAlbum extends DaoBase<Album> {

	@BindSqlSelect(childrenSelects={
			@BindSqlChildSelect(field="songs", method="selectByAlbumId")
	})
	List<Album> selectAlbums();
}
