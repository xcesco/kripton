package sqlite.feature.in.case4;

import java.sql.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;


@BindDao(City.class)
public interface DaoCity {
	
	@BindSqlInsert
	long insert(City bean);	
	
	@BindSqlSelect(where="date in (:{dates})")
	List<City> selectAll(/*@BindSqlParam(adapter=DateAdapter.class)*/ Date ... dates);
}
