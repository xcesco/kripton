/**
 * 
 */
package kripton15;

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
public class Bean1 implements Serializable {

	public Bean1()
	{
		map=new LinkedHashMap<>();
	}


	private static final long serialVersionUID = 3113613163524431347L;
	
	@Bind
	@BindXml(mapEntryStrategy=MapEntryType.ELEMENTS)
	Map<String, String> map;

}
