package sqlite.feature.jql.err2;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class BeanErr2 {

	@BindColumn(value="_id")
	public long id;

	@BindColumn(nullable = false)
	public String name;

}
