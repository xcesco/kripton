package sqlite.featJQL.entities;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;

public class Bean {

	@BindColumn(value="_id")
	public long id;

	@BindColumn(nullable = false)
	public String name;

}
