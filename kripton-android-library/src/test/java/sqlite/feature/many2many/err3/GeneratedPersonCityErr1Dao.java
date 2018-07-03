package sqlite.feature.many2many.err3;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import java.util.List;
import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Person;

@BindDao(PersonCityErr3.class)
@BindGeneratedDao(
    dao = PersonCityErr1Dao.class
)
@BindDaoMany2Many(
    entity1 = Person.class,
    entity2 = City.class
)
public interface GeneratedPersonCityErr1Dao extends PersonCityErr1Dao {
  @BindSqlSelect(
      where = "personId=:personId"
  )
  List<PersonCityErr3> selectByPersonId(@BindSqlParam("personId") long personId);

  @BindSqlSelect(
      where = "cityId=:cityId"
  )
  List<PersonCityErr3> selectByCityId(@BindSqlParam("cityId") long cityId);

  @BindSqlDelete(
      where = "id=:id"
  )
  int deleteById(@BindSqlParam("id") long id);

  @BindSqlDelete(
      where = "personId=:personId"
  )
  int deleteByPersonId(@BindSqlParam("personId") long personId);

  @BindSqlDelete(
      where = "cityId=:cityId"
  )
  int deleteByCityId(@BindSqlParam("cityId") long cityId);

  @BindSqlInsert
  int insert(@BindSqlParam("bean") PersonCityErr3 bean);
}
