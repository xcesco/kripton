package sqlite.feature.childselect.case2;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Song.class)
public interface DaoSong extends DaoBase<Song> {

	@BindSqlSelect
	List<Song> selectAll();
	
	@BindSqlSelect(where="albumId=${albumId}")
	ArrayList<Song> selectByAlbumId(@BindSqlParam("albumId") long dummy);
}
