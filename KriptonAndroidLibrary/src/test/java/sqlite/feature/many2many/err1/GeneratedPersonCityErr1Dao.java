package sqlite.feature.many2many.err1;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import java.util.List;
import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Person;

@BindDao(PersonCityErr1.class)
@BindGeneratedDao(
    dao = PersonCityErr1Dao.class,
    entity1 = Person.class,
    entity2 = City.class
)
public interface GeneratedPersonCityErr1Dao extends PersonCityErr1Dao {
  @BindSqlSelect(
      where = "id=${id}"
  )
  PersonCityErr1 selectById(@BindSqlParam("id") long id);

  @BindSqlSelect(
      where = "personId=${personId}"
  )
  List<PersonCityErr1> selectByPersonId(@BindSqlParam("personId") long personId);

  @BindSqlSelect(
      where = "cityId=${cityId}"
  )
  List<PersonCityErr1> selectByCityId(@BindSqlParam("cityId") long cityId);

  @BindSqlDelete(
      where = "id=${id}"
  )
  int deleteById(@BindSqlParam("id") long id);

  @BindSqlDelete(
      where = "personId=${personId}"
  )
  int deleteByPersonId(@BindSqlParam("personId") long personId);

  @BindSqlDelete(
      where = "cityId=${cityId}"
  )
  int deleteByCityId(@BindSqlParam("cityId") long cityId);

  @BindSqlInsert
  int insert(@BindSqlParam("bean") PersonCityErr1 bean);
}
