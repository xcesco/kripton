package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;

@BindDaoMany2Many(entity1=Person.class, entity2=City.class)
public interface PersonCityDao {
	
}
