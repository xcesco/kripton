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
public class Bean2 implements Serializable {

	public Bean2()
	{
		map=new LinkedHashMap<Integer, String>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	@BindElement(mapEntryPolicy=MapEntryStrategyType.ATTRIBUTES)
	Map<Integer, String> map;

}
