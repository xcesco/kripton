/**
 * 
 */
package issue.kripton_14;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.annotation.BindRoot;
import com.abubusoft.kripton.annotation.BindValue;



/**
 * @author xcesco
 *
 */
@BindRoot
public class Bean3 implements Serializable {

	public Bean3()
	{
		set=new HashSet<Bean0>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	@BindValue
	Set<Bean0> set;

}
