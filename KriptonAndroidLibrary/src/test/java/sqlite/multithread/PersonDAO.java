package sqlite.multithread;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Person.class)
public interface PersonDAO {

	@BindSqlInsert
	public void insertThread1(Person bean);
	
	@BindSqlInsert
	public void insertThread2(Person bean);
	
	@BindSqlSelect
	public Person selectThread1();
	
	@BindSqlSelect
	public Person selectThread2();
}