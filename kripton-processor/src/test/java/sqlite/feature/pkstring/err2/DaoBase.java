package sqlite.feature.pkstring.err2;

import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

public interface DaoBase<E> {

	@BindSqlUpdate(where="name=${bean.name}")
	void update(E bean);
}
