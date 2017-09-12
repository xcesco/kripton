package sqlite.feature.typeadapter;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Contact {

	public long id;

	public String name;

	@BindSqlAdapter(adapter = DateAdapterType.class, dataType = Long.class)
	public Date birthDay;
}
