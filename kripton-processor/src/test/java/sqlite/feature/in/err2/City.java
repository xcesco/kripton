package sqlite.feature.in.err2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class City {
	public long id;
	public String name;
	
	public List<Long> ids;
}
