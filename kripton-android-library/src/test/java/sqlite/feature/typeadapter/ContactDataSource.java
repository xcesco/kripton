package sqlite.feature.typeadapter;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = { ContactDao.class }, fileName = "contact.db")
public interface ContactDataSource {

}
