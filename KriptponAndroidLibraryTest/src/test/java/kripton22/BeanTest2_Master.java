/**
 * 
 */
package kripton22;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.database.ColumnType;

/**
 * <p>
 * Messaggio base per tutte le comunicazioni con il server.
 * </p>
 * 
 * @author Cesco
 * 
 */
@BindType
@BindTable
@BindAllFields
public class BeanTest2_Master {
	
	@BindColumn(ColumnType.PRIMARY_KEY)
	public long id;

	public String label;
	
	public Date date;

}
