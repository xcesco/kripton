/**
 * 
 */
package issue.kripton_15;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;
import com.abubusoft.kripton.binder.schema.MapEntryStrategyType;



/**
 * @author xcesco
 *
 */
@BindRoot
public class Bean1 implements Serializable {

	public Bean1()
	{
		map=new LinkedHashMap<>();
	}


	private static final long serialVersionUID = 3113613163524431347L;
	
	@BindElement(mapEntryPolicy=MapEntryStrategyType.ELEMENTS)
	Map<String, String> map;

}
