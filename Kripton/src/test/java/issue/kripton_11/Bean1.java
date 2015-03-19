/**
 * 
 */
package issue.kripton_11;

import java.io.Serializable;
import java.util.Date;

import com.abubusoft.kripton.annotation.BindAttribute;
import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;



/**
 * @author xcesco
 *
 */

@BindType
@BindTypeXml(name="name1", namespace="hello")
public class Bean1 implements Serializable {

	private static final long serialVersionUID = 3113613163524431347L;

	@BindType
	public static class SubBean01
	{
		@Bind(name="f2")
		public SubBean02 bean2;
		
		@BindAllFields
		public static class SubBean02
		{
			String fieldString;
			
			Long fieldLong;
		}
		
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
	
	@BindAttribute
	private String name;

	@BindAttribute
	private String surname;
	
	@Bind(name="f1")
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
