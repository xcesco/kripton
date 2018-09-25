package sqlite.errors.dao.case4;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Person {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
