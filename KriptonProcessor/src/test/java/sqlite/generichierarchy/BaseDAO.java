package sqlite.generichierarchy;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

public interface BaseDAO<E> {

	@BindSqlSelect(where ="id=${id}")
	E selectById(@BindSqlParam("id") long id);
	
	@BindSqlInsert
	public void insertThread1(E bean);
}
