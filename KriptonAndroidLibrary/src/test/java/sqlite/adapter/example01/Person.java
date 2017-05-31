package sqlite.adapter.example01;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Person {

	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String name;
	
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

	private String surname;
	
	@BindColumn("birth")	
	@BindAdapter(adapter=DateAdapter.class, dataType = Long.class)
	private Date birthDate;

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
