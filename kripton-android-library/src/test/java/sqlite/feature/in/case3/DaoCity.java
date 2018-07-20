package sqlite.feature.in.case3;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;


@BindDao(City.class)
public interface DaoCity {
	
	@BindSqlInsert
	long insert(City bean);
	
	@BindSqlSelect(where="id in (:{args})")
	List<City> selectAll(Long ... args);
}
