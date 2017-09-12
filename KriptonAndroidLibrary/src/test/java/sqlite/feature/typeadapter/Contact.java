package sqlite.feature.typeadapter;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindAdapter;

@BindTable
public class Contact {

	public long id;

	public String name;

	@BindAdapter(adapter = DateAdapterType.class, dataType = Long.class)
	public Date birthDay;
}
