package sqlite.feature.typeadapter.bitmap;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Person.class)
public interface DaoPerson {
	
	@BindSqlInsert
	int insert(Person bean);
	
	@BindSqlSelect
	List<Person> list();

}
