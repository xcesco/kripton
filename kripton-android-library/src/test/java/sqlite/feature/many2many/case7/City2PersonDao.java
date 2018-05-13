package sqlite.feature.many2many.case7;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;

@BindDaoMany2Many(entity1 = City.class, entity2 = Person.class)
public interface City2PersonDao {

}
