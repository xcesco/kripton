/**
 * 
 */
package issue.kripton_3;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot
public class Bean0 {

	@BindRoot
	public static class SubBean01
	{
		@BindElement
		private Date date;
		
		// Needed for serialization
		public SubBean01()
		{
			
		}
		
		public SubBean01(Date date, String title) {
			this.date=date;
			this.name=title;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@BindElement
		private String name;
	}
	
	@BindElement
	private Date birthday;
	
	@BindElement
	private String name;

	@BindElement
	private String surname;
	
	@BindElement
	private SubBean01[] tickets;
	

	public SubBean01[] getTickets() {
		return tickets;
	}

	public void setTickets(SubBean01[] tickets) {
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
