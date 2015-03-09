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
public class Bean6 extends Bean0 {

	@BindRoot
	public static class SubBean06
	{
		@BindElement
		private Date date;
		
		// Needed for serialization
		public SubBean06()
		{
			
		}
		
		public SubBean06(Date date, String title) {
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
	
	@BindElement List<SubBean06> tickets;
	


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
