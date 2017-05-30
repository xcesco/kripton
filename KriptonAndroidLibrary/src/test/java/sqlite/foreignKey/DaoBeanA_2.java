package sqlite.foreignKey;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(BeanA_2.class)
public interface DaoBeanA_2 {
	@BindSqlSelect
	List<BeanA_2> selectAll();
	
	@BindSqlSelect(where="id=${id}")
	List<BeanA_2> selectById(long id);
	
	@BindSqlSelect(fields="id",where="valueString2=${dummy}")
	List<BeanA_2> selectByString(@BindSqlParam("dummy") String value);
	
	@BindSqlInsert
	int insert(BeanA_2 bean);
	
	@BindSqlUpdate(where="valueString2=${bean.valueString2}")
	int update(BeanA_2 bean);
}
