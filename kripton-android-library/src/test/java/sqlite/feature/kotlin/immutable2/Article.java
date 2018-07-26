package sqlite.feature.kotlin.immutable2;

import java.net.URL;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.BindXmlNamespace;
import com.abubusoft.kripton.annotation.BindXmlType;


@BindType("item")
@BindXmlType(namespaces={
		@BindXmlNamespace(prefix="dc",uri="http://purl.org/dc/elements/1.1/"),
		@BindXmlNamespace(prefix="content",uri="http://purl.org/dc/elements/1.1/"),
})
@BindSqlType(name = "articles")
public class Article {
	private final long id;
	private final String title;
	public long getId() {
		return id;
	}

	/**
	 * 
	 */
	public Article() {
		super();
		this.id = 0;
		this.title = null;
		this.description = null;
		this.link = null;
		this.author = null;
		this.guid = null;
		this.comments = null;
		this.channelId = 0;
		this.thumbnail = null;
		this.read = false;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public URL getLink() {
		return link;
	}

	public String getAuthor() {
		return author;
	}

	public String getGuid() {
		return guid;
	}

	public URL getComments() {
		return comments;
	}

	public long getChannelId() {
		return channelId;
	}

	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	public boolean isRead() {
		return read;
	}

	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param link
	 * @param author
	 * @param guid
	 * @param comments
	 * @param channelId
	 * @param thumbnail
	 * @param read
	 */
	public Article(long id, String title, String description, URL link, String author, String guid, URL comments,
			long channelId, Thumbnail thumbnail, boolean read) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.link = link;
		this.author = author;
		this.guid = guid;
		this.comments = comments;
		this.channelId = channelId;
		this.thumbnail = thumbnail;
		this.read = read;
	}

	private final String description;
	private final URL link;
	private final String author;

	@BindSqlColumn(nullable = false, columnType = ColumnType.UNIQUE)
	private final String guid;

	private final URL comments;

	@BindSqlColumn(parentEntity = Channel.class)
	private final long channelId;

	@Bind("thumbnail")
	@BindXml(namespace="media")
	private final Thumbnail thumbnail;

	private final boolean read;
}
