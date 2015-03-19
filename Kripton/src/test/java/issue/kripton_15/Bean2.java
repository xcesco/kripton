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
public class Bean2 implements Serializable {

	public Bean2()
	{
		map=new LinkedHashMap<Integer, String>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	@Bind(mapEntryPolicy=MapEntryStrategyType.ATTRIBUTES)
	Map<Integer, String> map;

}
