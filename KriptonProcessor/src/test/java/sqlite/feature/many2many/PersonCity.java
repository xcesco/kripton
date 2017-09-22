package sqlite.feature.many2many;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class PersonCity {
	
	@BindColumn(columnType=ColumnType.PRIMARY_KEY)
	public long id;
	
	@BindColumn(foreignKey=Person.class)
	public long idPerson;
	
	@BindColumn(foreignKey=City.class)
	public long idCity;
}
