/**
 * 
 */
package kripton15;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean4 implements Serializable {

	private static final long serialVersionUID = 3113613163524431347L;

	public Date data;
	
	@Bind ArrayList<Level1> list;

	@Bind
	Map<String, Level1> map;
	
	@Bind Set<Level1> set;

	public String value;

	public Bean4()
	{
		map=new LinkedHashMap<>();
		list = new ArrayList<>();
		set=new LinkedHashSet<>();
	}

}
