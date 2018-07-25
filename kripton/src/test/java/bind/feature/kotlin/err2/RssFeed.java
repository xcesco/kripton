package bind.feature.kotlin.err2;

import java.util.List;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindSqlType
public class RssFeed {
	public RssFeed(long id, String uid) {
		super();
		this.id = id;
		this.uid = uid;
	}

	public long id;

	public long getId() {
		return id;
	}

	public String getUid() {
		return uid;
	}

	public String uid;
}