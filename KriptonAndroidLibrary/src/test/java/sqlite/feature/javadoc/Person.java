package sqlite.feature.javadoc;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Person {
	
	public long id;

	protected String name;
	
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

	public boolean isStudent() {
		return student;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

	protected String surname;
	
	protected boolean student;
	
}
