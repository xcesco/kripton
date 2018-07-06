package sqlite.feature.pkstring.case2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Album.class)
public interface DaoAlbum extends DaoBase<Album> {

	@BindSqlSelect(where="name=:name")
	List<Album> selectAlbums(String name);
	
	@BindSqlSelect(where="name=:bean.name")
	List<Album> selectAlbums2(Album bean);
}
