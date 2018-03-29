package sqlite.feature.typeadapter.bitmap;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName="person.db", version=1, daoSet = { DaoPerson.class })
public interface AppDataSource {

}
