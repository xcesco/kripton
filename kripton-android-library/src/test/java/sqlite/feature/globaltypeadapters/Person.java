package sqlite.feature.globaltypeadapters;

import java.sql.Date;

import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Person {

	public long id;
	
	public Date birthDay;
}
