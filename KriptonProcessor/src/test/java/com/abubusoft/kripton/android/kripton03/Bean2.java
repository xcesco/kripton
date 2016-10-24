/**
 * 
 */
package com.abubusoft.kripton.android.kripton03;

import java.io.Serializable;
import java.util.Date;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
public class Bean2 implements Serializable {

	private static final long serialVersionUID = 3113613163524431347L;

	@BindType
	public static class SubBean01
	{
		@Bind
		public SubBean02 sbean2;
		
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
