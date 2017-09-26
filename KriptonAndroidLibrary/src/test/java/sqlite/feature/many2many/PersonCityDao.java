package sqlite.feature.many2many;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

@BindContentProviderPath(path="test")
@BindDaoMany2Many(entity1=Person.class, entity2=City.class)
public interface PersonCityDao {

	@BindContentProviderEntry(path="${id}")
	@BindSqlSelect(where="id=${id}")
	void selett(long id, OnReadCursorListener listener);
}
