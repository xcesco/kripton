package sqlite.feature.in;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(City.class)
public interface DaoCity {

	@BindSqlSelect(where="id in (:{args})")
	List<City> selectAll(List<String> args);

	List<City> selectAll(String... args);
}
