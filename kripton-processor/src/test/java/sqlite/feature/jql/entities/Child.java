package sqlite.feature.jql.entities;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType 
public class Child extends Bean {

	@BindColumn(foreignKey=Person.class)
	public long parentId;
}
