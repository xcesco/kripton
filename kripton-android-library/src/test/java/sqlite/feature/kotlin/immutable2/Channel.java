package sqlite.feature.kotlin.immutable2;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindSqlType(name = "channels")
public class Channel {

	private final long id;
	private String title;

	@BindSqlColumn(columnType = ColumnType.UNIQUE)
	private final String link;

	private final String description;

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * @param id
	 * @param title
	 * @param link
	 * @param description
	 * @param language
	 * @param copyright
	 * @param pubDate
	 * @param lastBuildDate
	 * @param image
	 * @param rssFeedId
	 * @param articles
	 */
	public Channel(long id, String title, String link, String description, String language, String copyright,
			Date pubDate, Date lastBuildDate, Image image, long rssFeedId, List<Article> articles) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.copyright = copyright;
		this.pubDate = pubDate;
		this.lastBuildDate = lastBuildDate;
		this.image = image;
		this.rssFeedId = rssFeedId;
		this.articles = articles;
	}

	public Channel() {
		super();
		this.id = 0;
		this.title = null;
		this.link = null;
		this.description = null;
		this.language = null;
		this.copyright = null;
		this.pubDate = null;
		this.lastBuildDate = null;
		this.image = null;
		this.rssFeedId = 0;
		this.articles = null;
	}

	public String getLanguage() {
		return language;
	}

	public String getCopyright() {
		return copyright;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public Date getLastBuildDate() {
		return lastBuildDate;
	}

	public Image getImage() {
		return image;
	}

	public long getRssFeedId() {
		return rssFeedId;
	}

	public List<Article> getArticles() {
		return articles;
	}

	private final String language;
	private final String copyright;

	@BindAdapter(adapter = DateAdapter.class)
	private final Date pubDate;

	@BindAdapter(adapter = DateAdapter.class)
	private final Date lastBuildDate;

	private final Image image;

	@BindSqlColumn(parentEntity = RssFeed.class)
	private final long rssFeedId;

	@Bind("item")
	@BindSqlRelation(foreignKey = "channelId")
	private final List<Article> articles;
}
