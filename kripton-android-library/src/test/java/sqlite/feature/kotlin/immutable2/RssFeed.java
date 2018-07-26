package sqlite.feature.kotlin.immutable2;

import java.util.List;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;


@BindType(value = "rss")
public class RssFeed {
	private final long id;
	@BindSqlColumn(columnType = ColumnType.UNIQUE)
	private final String uid;

	public long getId() {
		return id;
	}
	
	public RssFeed() {
		super();
		this.id = 0;
		this.uid = null;
		this.version =null;
		this.channels = null;
	}

	/**
	 * @param id
	 * @param uid
	 * @param version
	 * @param channels
	 */
	public RssFeed(long id, String uid, String version, List<Channel> channels) {
		super();
		this.id = id;
		this.uid = uid;
		this.version = version;
		this.channels = channels;
	}

	public String getUid() {
		return uid;
	}

	public String getVersion() {
		return version;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	@BindXml(xmlType = XmlType.ATTRIBUTE)
	private final String version;

	@Bind("channel")
	@BindSqlRelation(foreignKey = "rssFeedId")
	private final List<Channel> channels;
}