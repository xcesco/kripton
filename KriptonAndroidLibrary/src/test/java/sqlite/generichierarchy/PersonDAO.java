package sqlite.generichierarchy;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.annotation.BindTypeVariables;

@BindTypeVariables(value="E", typeParameters=Person.class)
@BindDao(Person.class)
public interface PersonDAO extends BaseDAO<Person> {
//

//	
//	@BindSqlInsert
//	public void insertThread2(Person bean);
//	
//	@BindSqlSelect
//	public Person selectThread1();
//	
//	@BindSqlSelect
//	public Person selectThread2();
}