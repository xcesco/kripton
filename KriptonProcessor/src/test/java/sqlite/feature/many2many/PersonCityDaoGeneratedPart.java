package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.many2many.entity.PersonCityOk1;

import java.util.List;

@BindGeneratedDao(dao = PersonCityDao.class)
@BindDaoMany2Many(entity1 =  PersonCityOk1.class, entity2 =  PersonCityOk1.class)
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
