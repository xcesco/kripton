package sqlite.feature.livedata.paginated.case2;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType(name="groups")
public class Group {

	public long id;
	
	public String name;
}
