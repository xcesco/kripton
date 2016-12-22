package sqlite.foreignKey;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(BeanA_5.class)
public interface DaoBeanA_5 {
	@BindSqlSelect
	List<BeanA_5> selectAll();
	
	@BindSqlSelect(where="id=${id}")
	List<BeanA_5> selectById(long id);
	
	@BindSqlSelect(value="id",where="valueString=${dummy}")
	List<BeanA_5> selectByString(@BindSqlParam("dummy") String value);
	
	@BindSqlInsert
	int insert(BeanA_5 bean);
	
	@BindSqlUpdate(where="valueString=${bean.valueString}")
	int update(BeanA_5 bean);
}
