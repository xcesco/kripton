/**
 * 
 */
package issue.kripton_15;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.annotation.BindDefault;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot
@BindDefault
public class Bean2 implements Serializable {

	public Bean2()
	{
		set=new HashSet<Bean0>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	Set<Bean0> set;

}
