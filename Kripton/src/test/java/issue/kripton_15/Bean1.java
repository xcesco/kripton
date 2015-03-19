/**
 * 
 */
package issue.kripton_15;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.schema.MapEntryStrategyType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean1 implements Serializable {

	public Bean1()
	{
		map=new LinkedHashMap<>();
	}


	private static final long serialVersionUID = 3113613163524431347L;
	
	@Bind(mapEntryPolicy=MapEntryStrategyType.ELEMENTS)
	Map<String, String> map;

}
