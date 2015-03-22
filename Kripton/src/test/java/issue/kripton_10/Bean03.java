/**
 * 
 */
package issue.kripton_10;

import java.util.Date;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.XmlType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean03 {

	@BindType
	public static class SubBean03
	{
		@Bind
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

		@Bind
		private String name;
	}
	
	@Bind
	@BindXml(value=XmlType.ATTRIBUTE)
	private char car='a';
	
	@Bind
	@BindXml(value=XmlType.ATTRIBUTE)
	private Date birthday;
	
	@Bind
	@BindXml(value=XmlType.ATTRIBUTE)
	private String name;

	@Bind
	@BindXml(value=XmlType.ATTRIBUTE)
	private String surname;
	
	@Bind
	@BindXml(value=XmlType.ATTRIBUTE)
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
