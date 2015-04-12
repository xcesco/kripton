/**
 * 
 */
package kripton_14;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
@BindAllFields
public class Bean2 implements Serializable {

	public Bean2()
	{
		set=new HashSet<Bean0>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	Set<Bean0> set;

}
