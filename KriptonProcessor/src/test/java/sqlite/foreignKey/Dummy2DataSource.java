package sqlite.foreignKey;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName="test.db", value = { DaoBeanA_3.class, DaoBeanA_4.class })
public interface Dummy2DataSource {

}
