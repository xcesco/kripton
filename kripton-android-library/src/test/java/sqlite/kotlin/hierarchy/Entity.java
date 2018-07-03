package sqlite.kotlin.hierarchy;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Entity {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
