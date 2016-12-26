package sqlite.foreignKey;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName="test.db", dao = { DaoBeanA_5.class, DaoBeanA_6.class })
public interface Dummy3DataSource {

}
