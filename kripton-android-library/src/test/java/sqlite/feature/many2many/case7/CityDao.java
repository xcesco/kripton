package sqlite.feature.many2many.case7;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(City.class)
public interface CityDao {
	@BindSqlInsert
	public void insert(City bean);
	
	@BindSqlSelect
	public List<City> listCities();
}
