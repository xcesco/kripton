package sqlite.stack44330452;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class User {
	public long id;
	
	@BindColumn(enabled=false)
	public List<Pet> pets;
}
