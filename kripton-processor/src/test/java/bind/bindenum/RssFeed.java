package bind.bindenum;

import com.abubusoft.kripton.annotation.BindType;

@BindType
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