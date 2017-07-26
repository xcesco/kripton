package sqlite.feature.jql.entities;

import com.abubusoft.kripton.android.annotation.BindColumn;

public class Bean {

	@BindColumn(value="_id")
	public long id;

	@BindColumn(nullable = false)
	public String name;

}
