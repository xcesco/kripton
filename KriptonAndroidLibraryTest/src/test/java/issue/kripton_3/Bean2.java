/**
 * 
 */
package issue.kripton_3;

import java.io.Serializable;
import java.util.Date;

import com.abubusoft.kripton.annotation.BindDefault;
import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindDefault
public class Bean2 implements Serializable {

	private static final long serialVersionUID = 3113613163524431347L;

	@BindRoot
	public static class SubBean01
	{
		@BindElement
		public SubBean02 sbean2;
		
		@BindDefault
		public static class SubBean02
		{
			String fieldString;
			
			Long fieldLong;
		}
		
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
	
	private Date birthday;
	
	private String name;
	
	private String surname;
	
	public SubBean01 bean2;
	

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
