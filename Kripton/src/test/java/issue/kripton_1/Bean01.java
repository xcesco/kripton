/**
 * 
 */
package issue.kripton_1;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot
public class Bean01 {

	@BindElement
	private Date birthday;
	
	@BindElement
	private String name;

	@BindElement
	private String surname;
	
	@BindElement(elementName="ticket")
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
