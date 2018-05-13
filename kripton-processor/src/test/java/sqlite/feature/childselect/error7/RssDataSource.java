package sqlite.feature.childselect.error7;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName = "rss.db",daoSet = {DaoArticle.class, DaoChannel.class})
public interface RssDataSource {
}
