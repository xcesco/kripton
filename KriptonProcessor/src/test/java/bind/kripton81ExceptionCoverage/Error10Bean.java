package bind.kripton81ExceptionCoverage;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Error10Bean {

	@BindColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;

	public String name;

	public Date date;
}
