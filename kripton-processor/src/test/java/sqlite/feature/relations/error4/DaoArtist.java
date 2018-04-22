package sqlite.feature.relations.error4;

import com.abubusoft.kripton.android.annotation.BindDao;

@BindDao(Artist.class)
public interface DaoArtist extends DaoBase<Song> {

}
