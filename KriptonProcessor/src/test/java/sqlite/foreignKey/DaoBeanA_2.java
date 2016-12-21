package sqlite.foreignKey;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(BeanA_2.class)
public interface DaoBeanA_2 {
	@BindSqlSelect
	List<BeanA_2> selectAll();
}
