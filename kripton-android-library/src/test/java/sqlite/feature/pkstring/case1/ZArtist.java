package sqlite.feature.pkstring.case1;

import java.sql.Date;

import com.abubusoft.kripton.android.annotation.BindSqlType;


@BindSqlType
public class ZArtist {	
	public long id;
	
	public Date year;
	
	public String name;
}
