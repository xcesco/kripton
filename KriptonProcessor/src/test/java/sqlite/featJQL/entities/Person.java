package sqlite.featJQL.entities;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Person extends Bean {
	

	public List<Child> listChild;
	
	public byte[] image;
}
