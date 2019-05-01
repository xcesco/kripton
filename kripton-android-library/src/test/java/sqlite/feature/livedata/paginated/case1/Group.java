package sqlite.feature.livedata.paginated.case1;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType(name="person_groups")
public class Group {

	public long id;
	
	public String name;
}
