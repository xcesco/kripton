/**
 * 
 */
package issue.kripton_10;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindAttribute;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindOrder;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean01 {

	@BindAttribute
	private Date birthday;
	
	@BindOrder(value=0)
	@BindAttribute
	private char car='a';
	
	@BindAttribute
	private String name;

	@BindAttribute
	private String surname;
	
	@Bind(elementName="ticket")
	private int[] tickets;

	public int[] getTickets() {
		return tickets;
	}

	public void setTickets(int[] tickets) {
		this.tickets = tickets;
	}

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
