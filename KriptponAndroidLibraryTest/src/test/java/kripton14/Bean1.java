/**
 * 
 */
package kripton14;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean1 implements Serializable {

	public Bean1()
	{
		set=new HashSet<Bean0>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	@Bind(elementName="item")
	Set<Bean0> set;

}
