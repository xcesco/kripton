package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindTable(name="persons")
public class Person extends Entity  {

	public String name;
}
