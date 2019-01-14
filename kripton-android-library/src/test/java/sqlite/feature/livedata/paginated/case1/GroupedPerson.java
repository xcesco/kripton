package sqlite.feature.livedata.paginated.case1;

import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.annotation.BindType;

@BindSqlType
public class GroupedPerson {
	public long id;
	
	public String groupName;
	
	public String personName;
}
