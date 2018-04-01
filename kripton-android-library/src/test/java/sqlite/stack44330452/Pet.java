package sqlite.stack44330452;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Pet {
	public long id;

	@BindColumn(foreignKey = User.class)
	public long userId;
	
	public String name;

}
