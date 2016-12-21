package sqlite.foreignKey;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName="test.db", value = { DaoBeanA_1.class, DaoBeanA_2.class })
public interface DummyDataSource {

}
