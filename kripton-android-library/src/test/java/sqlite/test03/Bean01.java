/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.test03;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean01.
 */
@BindType
public class Bean01 {

	/** The lista. */
	protected List<Bean02> lista;
	
	/**
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public List<Bean02> getLista() {
		return lista;
	}

	/**
	 * Sets the lista.
	 *
	 * @param lista the lista to set
	 */
	public void setLista(List<Bean02> lista) {
		this.lista = lista;
	}

	/** The id. */
	protected long id;
	
	/** The message date. */
	protected long messageDate;
	
	/** The message text. */
	@BindColumn(nullable=false)
	protected String messageText;
	
	/**
	 * Gets the message date.
	 *
	 * @return the messageDate
	 */
	public long getMessageDate() {
		return messageDate;
	}

	/**
	 * Sets the message date.
	 *
	 * @param messageDate the messageDate to set
	 */
	public void setMessageDate(long messageDate) {
		this.messageDate = messageDate;
	}

	/**
	 * Gets the message text.
	 *
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * Sets the message text.
	 *
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/** The bean list. */
	protected List<Bean02> beanList;
	
	/**
	 * Gets the bean list.
	 *
	 * @return the beanList
	 */
	public List<Bean02> getBeanList() {
		return beanList;
	}

	/**
	 * Sets the bean list.
	 *
	 * @param beanList the beanList to set
	 */
	public void setBeanList(List<Bean02> beanList) {
		this.beanList = beanList;
	}



	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public long getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}

	/** The value. */
	protected long value;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
