package bind.feature.kotlin.err2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoRssFeed.class}, fileName = "app.db")
public interface AppDataSource {

}
