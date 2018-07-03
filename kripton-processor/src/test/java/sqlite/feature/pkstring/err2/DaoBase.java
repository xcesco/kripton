package sqlite.feature.pkstring.err2;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;

public interface DaoBase<E> {

	@BindSqlInsert
	void update(E bean);
}
