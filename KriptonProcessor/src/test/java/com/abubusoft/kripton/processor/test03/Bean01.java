package com.abubusoft.kripton.processor.test03;

import java.util.List;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class Bean01 {

	protected List<Bean02> lista;
	
	/**
	 * @return the lista
	 */
	public List<Bean02> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<Bean02> lista) {
		this.lista = lista;
	}

	protected long id;
	
	protected long messageDate;
	
	@BindColumn(nullable=false)
	protected String messageText;
	
	/**
	 * @return the messageDate
	 */
	public long getMessageDate() {
		return messageDate;
	}

	/**
	 * @param messageDate the messageDate to set
	 */
	public void setMessageDate(long messageDate) {
		this.messageDate = messageDate;
	}

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	protected List<Bean02> beanList;
	
	/**
	 * @return the beanList
	 */
	public List<Bean02> getBeanList() {
		return beanList;
	}

	/**
	 * @param beanList the beanList to set
	 */
	public void setBeanList(List<Bean02> beanList) {
		this.beanList = beanList;
	}



	/**
	 * @return the value
	 */
	public long getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}

	protected long value;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
