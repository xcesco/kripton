package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable(name="cities")
public class City extends Entity {

	public String name;
}
