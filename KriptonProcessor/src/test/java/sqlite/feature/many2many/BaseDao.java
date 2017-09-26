package sqlite.feature.many2many;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

public interface BaseDao<E> {
	
	@BindSqlSelect
	List<E> selectAll();
	
	@BindSqlInsert
	long insert(E bean);
	
	@BindSqlSelect(where="id=${id}")
	E selectById(long id);
	
	@BindSqlDelete(where="id=${id}")
	int deleteById(long id);

}
