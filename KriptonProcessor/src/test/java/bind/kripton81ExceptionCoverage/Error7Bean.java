package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Error7Bean {

	@BindColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;

	@BindDisabled
	@BindColumn
	public String ignore;

	@BindColumn(enabled = false)
	public String ignore2;

	@BindColumn(foreignKey = Error7_1Bean.class)
	public Error7_1Bean foreign;
}
