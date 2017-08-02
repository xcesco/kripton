package sqlite.feature.schema.version1;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;

public interface DaoBase<E> {

	@BindSqlInsert
	public long insert(E bean);
}
