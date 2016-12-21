package sqlite.foreignKey;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(BeanA_1.class)
public interface DaoBeanA_1 {

	@BindSqlSelect
	List<BeanA_1> selectAll();
}
