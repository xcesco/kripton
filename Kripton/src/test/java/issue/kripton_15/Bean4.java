/**
 * 
 */
package issue.kripton_15;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot
public class Bean4 implements Serializable {

	private static final long serialVersionUID = 3113613163524431347L;

	public Date data;
	
	@BindElement ArrayList<Level1> list;

	@BindElement
	Map<String, Level1> map;
	
	@BindElement Set<Level1> set;

	public String value;

	public Bean4()
	{
		map=new LinkedHashMap<>();
		list = new ArrayList<>();
		set=new LinkedHashSet<>();
	}

}
