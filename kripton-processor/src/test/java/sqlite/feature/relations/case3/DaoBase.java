package sqlite.feature.relations.case3;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;

public interface DaoBase<E> {

	@BindSqlInsert
	void insert(E bean);
}
