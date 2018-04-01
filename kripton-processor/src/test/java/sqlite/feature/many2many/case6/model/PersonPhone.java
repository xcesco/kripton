package sqlite.feature.many2many.case6.model;

import java.sql.Date;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class PersonPhone {

	public long id;
	@BindColumn(foreignKey=Person.class)
	public long personId;
	
	@BindColumn(foreignKey=PhoneNumber.class)
	public long phoneNumberId;
	public Date buyDate;
}
