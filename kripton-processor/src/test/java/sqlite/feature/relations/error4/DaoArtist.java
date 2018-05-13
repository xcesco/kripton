package sqlite.feature.relations.error4;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Artist.class)
public interface DaoArtist extends DaoBase<Song> {

	@BindSqlSelect(where="album=${albumId}")
	public List<Artist> selectByAlbumId(long albumId);
}
