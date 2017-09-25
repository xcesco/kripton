package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindDaoGeneratedPart;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDaoGeneratedPart(
    dao = PersonCityDao.class,
    entity = PersonCity.class
)
public interface PersonCityDaoGeneratedPart {
  @BindSqlSelect(
      where = "id=${id}"
  )
  PersonCity selectByid(@BindSqlParam("id") long id);

  @BindSqlSelect(
      where = "id=${id}"
  )
  PersonCity selectByPersonid(@BindSqlParam("id") long id);
}
