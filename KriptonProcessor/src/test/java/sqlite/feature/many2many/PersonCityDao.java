package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindMany2Many;

@BindDao
@BindMany2Many(entity1=Person.class, entity2=City.class)
public interface PersonCityDao {

}
