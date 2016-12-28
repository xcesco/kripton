package sqlite.foreignKey;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(BeanA_3.class)
public interface DaoBeanA_3 {
	@BindSqlSelect
	List<BeanA_3> selectAll();
	
	@BindSqlSelect(where="id=${id}")
	List<BeanA_3> selectById(long id);
	
	@BindSqlSelect(value="id",where="valueString2=${dummy}")
	List<BeanA_3> selectByString(@BindSqlParam("dummy") String value);
	
	@BindSqlInsert
	int insert(BeanA_3 bean);
	
	@BindSqlUpdate(where="valueString2=${bean.valueString2}")
	int update(BeanA_3 bean);
}
