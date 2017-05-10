package sqlite.paginatedResult;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindTable
public class Person {
	public long id;

	@BindColumn(columnType = ColumnType.INDEXED)
	public String name;

	public String surname;
	public String birthCity;
	public Date birthDay;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", birthCity=");
		builder.append(birthCity);
		builder.append(", birthDay=");
		builder.append(birthDay);
		builder.append("]");
		return builder.toString();
	}

}