package sqlite.feature.in.err2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindContentProviderPath(path = "city")
@BindDao(City.class)
public interface DaoCity {

	@BindSqlInsert
	long insert(City bean);

	@BindContentProviderEntry(path="all/:{dummy}")
	@BindSqlSelect(where = "id in (:{dummy})")
	List<City> selectAll2(@BindSqlParam("dummy") List<Long> ids);
}
