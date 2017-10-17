package sqlite.feature.many2many.entity;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import java.util.List;
import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Person;

@BindDao(PersonCityOk1.class)
@BindGeneratedDao(
    dao = PersonCityOk1Dao.class,
    entity1 = Person.class,
    entity2 = City.class
)
public interface GeneratedPersonCityOk1Dao extends PersonCityOk1Dao {
  @BindSqlSelect(
      where = "id=${id}"
  )
  PersonCityOk1 selectById(@BindSqlParam("id") long id);

  @BindSqlSelect(
      where = "personId=${personId}"
  )
  List<PersonCityOk1> selectByPersonId(@BindSqlParam("personId") long personId);

  @BindSqlSelect(
      where = "cityId=${cityId}"
  )
  List<PersonCityOk1> selectByCityId(@BindSqlParam("cityId") long cityId);

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
  int insert(@BindSqlParam("bean") PersonCityOk1 bean);
}
