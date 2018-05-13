package sqlite.feature.relations.case4.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.relations.case4.model.Article;

@BindDao(Article.class)
public interface DaoArticle extends DaoBase<Article> {
	
	@BindSqlSelect(where="channelId=${channelId}")
	List<Article> selectByChannel(long channelId);
	
	@BindSqlSelect
	List<Article> selectAll();
}
