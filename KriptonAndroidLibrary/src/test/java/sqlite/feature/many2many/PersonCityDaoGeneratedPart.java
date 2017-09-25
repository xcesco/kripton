package sqlite.feature.many2many;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDaoGeneratedPart;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDaoGeneratedPart(
    dao = PersonCityDao.class,
    entity = PersonCity.class
)
public interface PersonCityDaoGeneratedPart {
  @BindSqlSelect(where = "id=${id}")
  PersonCity selectById(@BindSqlParam("id") long id);
  
  @BindSqlSelect(where = "cityId=${cityId}")
  List<PersonCity> selectByCityId(@BindSqlParam("cityId") long cityId);
  
  @BindSqlSelect(where = "personId=${personId}")
  List<PersonCity> selectByPersonId(@BindSqlParam("personId") long personId);
}
