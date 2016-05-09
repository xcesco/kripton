/**
 * 
 */
package kripton22;

import java.math.BigDecimal;
import java.net.URL;

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
public class BeanTest2_Detail {
	
	@BindColumn(ColumnType.PRIMARY_KEY)
	public long id;
	
	@BindColumn(value=ColumnType.FOREIGN_KEY)
	public BeanTest2_Master master;
	
	public URL url;
	
	public String description;
	
	public BigDecimal value;

}
