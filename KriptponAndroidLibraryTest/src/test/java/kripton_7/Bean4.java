/**
 * 
 */
package kripton_7;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean4 extends Bean0 {

	@Bind
	private Date birthday;
	
	@Bind
	private String name;

	@Bind
	private String surname;
	
	@Bind List<Integer> tickets;

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
