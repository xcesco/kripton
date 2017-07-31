package sqlite.feature.schema.data;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;

public interface DaoBase<E> {

	@BindSqlInsert
	public long insert(E bean);
}
