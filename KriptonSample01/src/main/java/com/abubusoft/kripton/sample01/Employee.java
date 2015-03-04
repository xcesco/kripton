/**
 * 
 */
package com.abubusoft.kripton.sample01;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.binder.annotation.BindElement;
import com.abubusoft.kripton.binder.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot
public class Employee {

	@BindElement
	private Date birthday;
	
	@BindElement
	private String name;

	@BindElement
	private String surname;
	
	@BindElement(elementName="ticket")
	private List<Integer> tickets;

	public List<Integer> getTickets() {
		return tickets;
	}

	public void setTickets(List<Integer> tickets) {
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
