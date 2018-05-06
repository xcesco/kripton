package sqlite.feature.childselect.error6;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;



@BindDao(Article.class)
public interface DaoArticle extends DaoBase<Article> {
	
	@BindSqlSelect(where="channelId=${channelId}")
	List<Article> selectByChannel(long channelId);
	
	@BindSqlSelect(where="title=${title}")
	List<Article> selectByTitle(String title);
}
