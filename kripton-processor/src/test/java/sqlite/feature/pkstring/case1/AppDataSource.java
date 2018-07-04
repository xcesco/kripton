package sqlite.feature.pkstring.case1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoZArtist.class, DaoSong.class, DaoAlbum.class}, fileName="pk_string_case1.db", schema=true)
public interface AppDataSource {

}
