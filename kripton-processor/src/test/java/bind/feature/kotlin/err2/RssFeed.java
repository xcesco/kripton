package bind.feature.kotlin.err2;

import java.util.List;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType("rss")
public final class RssFeed {
	@BindSqlColumn(columnType = ColumnType.UNIQUE)
	private String uid;
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	private String version;
	@Bind("channel")
	@BindSqlRelation(foreignKey = "rssFeedId")
	private List channels;
	private long id;

	public final String getUid() {
		return this.uid;
	}

	public final void setUid(String var1) {
		this.uid = var1;
	}

	public final String getVersion() {
		return this.version;
	}

	public final void setVersion(String var1) {
		this.version = var1;
	}

	public final List getChannels() {
		return this.channels;
	}

	public final void setChannels(List var1) {
		this.channels = var1;
	}

	public final long getId() {
		return this.id;
	}

	public final void setId(long var1) {
		this.id = var1;
	}

	public RssFeed(String uid, String version, List channels, long id) {
		this.uid = uid;
		this.version = version;
		this.channels = channels;
		this.id = id;
	}
}