/**
 * 
 */
package kripton03;

import java.util.Date;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean0 {

	@BindType
	public static class SubBean01
	{
		@Bind
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

		@Bind
		private String name;
	}
	
	@Bind
	private Date birthday;
	
	@Bind	
	private String name;

	@Bind
	private String surname;
	
	@Bind
	private byte[] buffer={1, 2,3, 4};
	
	@Bind
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
