package bind.directmap;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Person {

	public String name;
	
	public String surname;
	
	public Date birthday;
	
	public List<String> tags;
}
