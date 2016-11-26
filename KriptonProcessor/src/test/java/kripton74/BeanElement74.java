package kripton74;

import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

@BindType
public class BeanElement74 {
	
	public BeanElement74()
	{
		
	}
	
	public BeanElement74(String name)
	{
		this.name=name;
	}
	
	public String name;
	
	@Bind
	public Map<String, Integer> valueMapStringInteger;	
	
}
