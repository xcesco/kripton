package sqlite.feature.in.case4;

import com.abubusoft.kripton.android.annotation.BindSqlType;
import java.sql.Date;

@BindSqlType
public class City {
	public long id;
	
	public String name;
	
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
