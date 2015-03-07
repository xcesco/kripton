/**
 * 
 */
package issue.kripton_7;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot
public class Bean5  extends Bean0{

	@BindElement
	private Date birthday;
	
	@BindElement
	private String name;

	@BindElement
	private String surname;
	
	@BindElement List<Integer> tickets;


	public Date getBirthday() {
		return birthday;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
