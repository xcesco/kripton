package sqlite.feature.relations.error5;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName = "rss.db",daoSet = {DaoArticle.class, DaoChannel.class})
public interface RssDataSource {
}
