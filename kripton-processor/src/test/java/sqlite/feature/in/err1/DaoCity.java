package sqlite.feature.in.err1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(City.class)
public interface DaoCity {
	
	@BindSqlInsert
	long insert(City bean);
	
	@BindSqlSelect(where="id in (:{dummy.ids})")
	List<City> selectAll2(@BindSqlParam("dummy") City bean);
}
