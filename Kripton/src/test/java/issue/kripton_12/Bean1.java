/**
 * 
 */
package issue.kripton_12;

import java.io.Serializable;
import java.util.Date;

import com.abubusoft.kripton.annotation.BindAttribute;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean1 extends Bean0<BeanGeneric, Integer> implements Serializable {

	private static final long serialVersionUID = 3113613163524431347L;

	@Bind
	private Date birthday;
	
	@BindAttribute
	private String name;

	@BindAttribute
	private String surname;
	
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
