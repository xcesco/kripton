package sqlite.feature.relations.error4;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = { DaoAlbum.class, DaoSong.class, DaoArtist.class }, fileName = "app.db")
public interface AppDataSource {

}
