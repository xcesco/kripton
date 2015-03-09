/**
 * 
 */
package issue.kripton_10;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindAttribute;
import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot
public class Bean03 {

	@BindRoot
	public static class SubBean03
	{
		@BindElement
		private Date date;
		
		// Needed for serialization
		public SubBean03()
		{
			
		}
		
		public SubBean03(Date date, String title) {
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
	
	@BindAttribute
	private char car='a';
	
	@BindAttribute
	private Date birthday;
	
	@BindAttribute
	private String name;

	@BindAttribute
	private String surname;
	
	@BindAttribute
	private SubBean03[] tickets;
	

	public SubBean03[] getTickets() {
		return tickets;
	}

	public void setTickets(SubBean03[] tickets) {
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
