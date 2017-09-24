package sqlite.feature.many2many;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlSelect;

public interface BaseDao<E> {
	
	@BindSqlSelect
	public List<E> selectAll();

}
