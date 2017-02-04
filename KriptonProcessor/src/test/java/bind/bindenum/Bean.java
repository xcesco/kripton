package bind.bindenum;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.sqlite.FieldType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean {
	

	@Bind()
	@BindColumn(fieldType=FieldType.ENUM)
	public LogLevel level;
	
}
