package sqlite.paginatedResult;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindTable
public class Err1Person {
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
		builder.append(", ");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (surname != null) {
			builder.append("surname=");
			builder.append(surname);
			builder.append(", ");
		}
		if (birthCity != null) {
			builder.append("birthCity=");
			builder.append(birthCity);
			builder.append(", ");
		}
		if (birthDay != null) {
			builder.append("birthDay=");
			builder.append(birthDay);
		}
		builder.append("]");
		return builder.toString();
	}

}