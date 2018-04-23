package sqlite.feature.relations.error1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoAlbum.class, DaoSong.class}, fileName="app.db")
public interface AppDataSource {

}
