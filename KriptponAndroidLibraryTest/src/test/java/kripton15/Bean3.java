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
public class Bean3 implements Serializable {

	public Bean3()
	{
		map=new LinkedHashMap<>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	@Bind
	Map<Bean0, Bean0> map;

}
