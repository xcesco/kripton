/**
 * 
 */
package kripton15;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean8<K,V, A> implements Serializable {

	public Bean8()
	{
		mapField=new LinkedHashMap<K, V>();
	}
	
	@Bind	
	public Map<K,V> mapField; 

	private static final long serialVersionUID = 3113613163524431347L;

}
