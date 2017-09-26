package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable(name="persons")
public class Person extends Entity  {

	public String name;
}
