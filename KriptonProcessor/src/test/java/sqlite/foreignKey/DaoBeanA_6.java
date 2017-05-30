package sqlite.foreignKey;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(BeanA_6.class)
public interface DaoBeanA_6 {

	@BindSqlSelect
	List<BeanA_6> selectAll();
	
	@BindSqlSelect(where="id=${id}")
	List<BeanA_6> selectById(long id);
	
	@BindSqlSelect(fields="id",where="valueString2=${dummy}")
	List<BeanA_6> selectByString(@BindSqlParam("dummy") String value);
	
	@BindSqlInsert
	int insert(BeanA_6 bean);
	
	@BindSqlUpdate(where="valueString2=${bean.valueString2}")
	int update(BeanA_6 bean);
}
