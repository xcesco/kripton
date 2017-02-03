package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Error9Bean {

	@BindColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;

	public String name;

	public java.sql.Date date;
}
