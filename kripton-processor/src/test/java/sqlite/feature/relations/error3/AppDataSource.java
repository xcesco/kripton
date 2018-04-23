package sqlite.feature.relations.error3;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = { DaoAlbum.class, DaoSong.class, DaoArtist.class }, fileName = "app.db")
public interface AppDataSource {

}
