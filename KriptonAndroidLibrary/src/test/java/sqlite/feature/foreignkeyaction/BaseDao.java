package sqlite.feature.foreignkeyaction;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

public interface BaseDao<E> {

	@BindSqlSelect(where="id=${id}")
	E selectById(long id);
	
	@BindSqlSelect
	List<E> selectAll();
	
	@BindSqlUpdate(where="id=${bean.id}")
	long update(E bean);
	
	@BindSqlInsert
	long insert(E bean);
	
	@BindSqlDelete(where="id=${id}")
	long deleteById(long id);
}
