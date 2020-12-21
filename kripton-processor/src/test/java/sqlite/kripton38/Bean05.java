/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.kripton38;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;


/**
 * The Class Bean05.
 */
@BindType
@BindSqlType(name="ws_bean")
public class Bean05 {

	/** The pk. */
	@Bind
	@BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
	protected long pk;

	/** The number. */
	@Bind
	@BindSqlColumn
	protected long number;

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number            the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/** The bean type. */
	@Bind
	protected BeanType beanType;

	/**
	 * Gets the bean type.
	 *
	 * @return the beanType
	 */
	public BeanType getBeanType() {
		return beanType;
	}

	/**
	 * Sets the bean type.
	 *
	 * @param beanType            the beanType to set
	 */
	public void setBeanType(BeanType beanType) {
		this.beanType = beanType;
	}

	/**
	 * Gets the pk.
	 *
	 * @return the pk
	 */
	public long getPk() {
		return pk;
	}

	/**
	 * Sets the pk.
	 *
	 * @param pk            the pk to set
	 */
	public void setPk(long pk) {
		this.pk = pk;
	}

	/** The text. */
	@Bind
	@BindSqlColumn
	protected String text;

	/** The content. */
	@Bind
	@BindSqlColumn
	protected byte[] content;

	/** The creation time. */
	@Bind
	@BindSqlColumn
	protected Date creationTime;

	/**
	 * Gets the creation time.
	 *
	 * @return the creationTime
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * Sets the creation time.
	 *
	 * @param creationTime            the creationTime to set
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content            the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
