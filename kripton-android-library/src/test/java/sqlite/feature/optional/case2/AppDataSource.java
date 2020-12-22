package sqlite.feature.optional.case2;

import com.abubusoft.kripton.android.annotation.BindDataSource;


@BindDataSource(fileName = "app.db",
        daoSet = { DaoArticle.class, DaoArtist.class},
        asyncTask = true)
public interface AppDataSource {
}
