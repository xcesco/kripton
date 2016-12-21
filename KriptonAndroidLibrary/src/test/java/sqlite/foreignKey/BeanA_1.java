package sqlite.foreignKey;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class BeanA_1 {

	public long id;
	
	@BindColumn(foreignKey=BeanA_2.class)
	public long beanA2Id;
}
