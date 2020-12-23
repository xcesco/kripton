package sqlite.feature.optional.case6;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindContentProvider(authority = "com.abubusoft.kripton.example")
@BindDataSource(fileName = "app.db",
        daoSet = { DaoArticle.class, DaoArtist.class},
        asyncTask = true)
public interface AppDataSource {
}
