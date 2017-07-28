package sqlite.feature.jql.entities;

import java.util.List;

import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Person extends Bean {
	
	@BindDisabled
	public List<Child> listChild;
	
	public byte[] image;
}
