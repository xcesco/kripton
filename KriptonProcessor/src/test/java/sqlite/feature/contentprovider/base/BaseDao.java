package sqlite.feature.contentprovider.base;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

/**
 * Created by xcesco on 02/10/2017.
 */

public interface BaseDao<E> {
	@BindContentProviderEntry(path = "${id}")
	@BindSqlSelect(where = "id=${id}")
	E selectById(long id);

	@BindSqlSelect
	List<E> selectAll();

	@BindSqlInsert
	long insert(E bean);

	@BindSqlUpdate(where = "id=${bean.id}")
	long update(E bean);

	@BindSqlDelete(where = "id=${bean.id}")
	long delete(E bean);

}
