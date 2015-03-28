/**
 * 
 */
package com.abubusoft.kripton.examples.example01;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindAllFields;



@BindAllFields
public class Employee {

	private Date birthday;
	
	private String name;

	private String surname;
	
	private int[] tickets;

	public int[] getTickets() {
		return tickets;
	}

	public void setTickets(int[] tickets) {
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
