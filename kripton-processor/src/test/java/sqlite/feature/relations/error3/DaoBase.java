package sqlite.feature.relations.error3;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

public interface DaoBase<E> {

	@BindSqlInsert
	void insert(E bean);

	@BindSqlSelect
	List<E> selectAll();
}
