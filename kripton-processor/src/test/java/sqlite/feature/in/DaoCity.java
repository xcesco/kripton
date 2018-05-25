package sqlite.feature.in;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(City.class)
public interface DaoCity {

	@BindSqlSelect
	List<City> selectAll();
}
