package sqlite.feature.javadoc;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Person {
	
	public long id;

	protected String personName;
	
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonSurname() {
		return personSurname;
	}

	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
	}

	public boolean isStudent() {
		return student;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

	protected String personSurname;
	
	protected boolean student;
	
}
