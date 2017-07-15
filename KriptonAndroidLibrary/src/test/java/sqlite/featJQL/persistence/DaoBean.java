package sqlite.featJQL.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

public interface DaoBean<E> {

	@BindSqlSelect
	public List<E> selectAll();

	@BindSqlInsert
	public E insert(E bean);

}
