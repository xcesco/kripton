package issue.kripton_12;

import com.abubusoft.kripton.annotation.BindDefault;

@BindDefault
public class BeanGeneric {

	public BeanGeneric()
	{
		
	}
	
	public BeanGeneric(String value1, Integer value2)
	{
		this.value1=value1;
		this.value2=value2;
	}
	
	public String value1;
	
	public Integer value2;
}
