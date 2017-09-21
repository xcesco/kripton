package sqlite.feature.typeadapter.insert.err2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = { ErrContactDao.class }, fileName = "contact.db")
public interface ErrContactDataSource {

}
