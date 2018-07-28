package sqlite.feature.paginatedresult.case1;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Person {

	private long id;
	
	public Person(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private String name;
}
