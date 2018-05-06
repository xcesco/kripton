package sqlite.feature.relations.error6;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName = "rss.db",daoSet = {DaoArticle.class, DaoChannel.class})
public interface RssDataSource {
}
