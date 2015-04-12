/**
 * 
 */
package kripton_15;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.internal.MapEntryType;



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

	@Bind(mapKeyName="k", mapValueName="v")
	@BindXml(mapEntryStrategy=MapEntryType.ATTRIBUTES)
	Map<Integer, String> map;

}
