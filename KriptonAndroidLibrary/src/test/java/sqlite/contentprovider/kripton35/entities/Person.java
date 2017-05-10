package sqlite.contentprovider.kripton35.entities;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Person {

	public long id;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	protected String name;
	
	protected String surname;
}
