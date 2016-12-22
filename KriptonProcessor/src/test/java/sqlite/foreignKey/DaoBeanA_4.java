package sqlite.foreignKey;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(BeanA_4.class)
public interface DaoBeanA_4 {

	@BindSqlSelect
	List<BeanA_4> selectAll();
	
	@BindSqlSelect(where="id=${id}")
	List<BeanA_4> selectById(long id);
	
	@BindSqlSelect(value="id",where="valueString=${dummy}")
	List<BeanA_4> selectByString(@BindSqlParam("dummy") String value);
	
	@BindSqlInsert
	int insert(BeanA_4 bean);
	
	@BindSqlUpdate(where="valueString=${bean.valueString}")
	int update(BeanA_4 bean);
}
