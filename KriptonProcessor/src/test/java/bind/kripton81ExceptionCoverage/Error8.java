package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Error8 {

	@BindColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;

	@BindDisabled
	public String ignore;

	public String ignore2;

}
