package sqlite.feature.many2many.case7;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import java.util.List;

@BindDao(CityPerson.class)
@BindGeneratedDao(
    dao = City2PersonDao.class
)
@BindDaoMany2Many(
    entity1 = City.class,
    entity2 = Person.class
)
public interface GeneratedCity2PersonDao extends City2PersonDao {
  @BindSqlSelect(
      where = "id=${id}"
  )
  CityPerson selectById(@BindSqlParam("id") long id);

  @BindSqlSelect(
      where = "cityId=${cityId}"
  )
  List<CityPerson> selectByCityId(@BindSqlParam("cityId") long cityId);

  @BindSqlSelect(
      where = "personId=${personId}"
  )
  List<CityPerson> selectByPersonId(@BindSqlParam("personId") long personId);

  @BindSqlDelete(
      where = "id=${id}"
  )
  int deleteById(@BindSqlParam("id") long id);

  @BindSqlDelete(
      where = "cityId=${cityId}"
  )
  int deleteByCityId(@BindSqlParam("cityId") long cityId);

  @BindSqlDelete(
      where = "personId=${personId}"
  )
  int deleteByPersonId(@BindSqlParam("personId") long personId);

  @BindSqlInsert
  int insert(@BindSqlParam("bean") CityPerson bean);
}
