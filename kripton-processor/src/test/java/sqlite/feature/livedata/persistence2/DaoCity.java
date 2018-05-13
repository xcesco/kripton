package sqlite.feature.livedata.persistence2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;


@BindDao(City.class)
public interface DaoCity {

	@BindSqlSelect
	List<City> selectAll();
}
