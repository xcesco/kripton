package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindDaoGeneratedPart;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import java.util.List;

@BindDaoGeneratedPart(dao = PersonCityDao.class, entity = PersonCity.class)
public interface PersonCityDaoGeneratedPart {
	@BindSqlSelect(where = "id=${id}")
	PersonCity selectById(@BindSqlParam("id") long id);

	@BindSqlSelect(where = "personId=${personId}")
	List<PersonCity> selectByPersonId(@BindSqlParam("personId") long personId);

	@BindSqlSelect(where = "cityId=${cityId}")
	List<PersonCity> selectByCityId(@BindSqlParam("cityId") long cityId);

	@BindSqlDelete(where = "id=${id}")
	int deleteById(@BindSqlParam("id") long id);

	@BindSqlDelete(where = "personId=${personId}")
	int deleteByPersonId(@BindSqlParam("personId") long personId);

	@BindSqlDelete(where = "cityId=${cityId}")
	int deleteByCityId(@BindSqlParam("cityId") long cityId);

	@BindSqlInsert
	int insert(@BindSqlParam("bean") PersonCity bean);
}
