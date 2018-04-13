package sqlite.feature.relations.case2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoAlbum.class, DaoSong.class}, fileName="app.db")
public interface AppDataSource {

}
