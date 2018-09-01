package sqlite.feature.kotlin.immutable2;

import com.abubusoft.kripton.android.annotation.BindDataSource;


@BindDataSource(fileName = "rss.db",
        daoSet = {DaoRss.class, DaoArticle.class, DaoChannel.class},
        asyncTask = true)
public interface RssDataSource {
}
