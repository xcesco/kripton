package sqlite.git19.case4;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(log=true, daoSet = { DaoDocument.class }, fileName = "document.db")
public interface DocumentDataSource {

}
