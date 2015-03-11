/**
 * 
 */
package issue.kripton_13;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot
public class Bean1 implements Serializable {

	public Bean1()
	{
		list=new LinkedList<Bean0>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	@BindElement(elementName="item")
	List<Bean0> list;

}
