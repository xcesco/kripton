package sqlite.feature.relations.error3;

import com.abubusoft.kripton.android.annotation.BindDao;

@BindDao(Artist.class)
public interface DaoArtist extends DaoBase<Song> {

}
