/**
 * 
 */
package issue.kripton_11;

import java.io.Serializable;
import java.util.Date;

import com.abubusoft.kripton.annotation.BindAttribute;
import com.abubusoft.kripton.annotation.BindDefault;
import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot(name="name1", onlyChildren=false, namespace="hello")
public class Bean1 implements Serializable {

	private static final long serialVersionUID = 3113613163524431347L;

	@BindRoot
	public static class SubBean01
	{
		@BindElement(name="f2")
		public SubBean02 bean2;
		
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
	
	@BindElement
	private Date birthday;
	
	@BindAttribute
	private String name;

	@BindAttribute
	private String surname;
	
	@BindElement(name="f1")
	public SubBean01 bean1;
	

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
