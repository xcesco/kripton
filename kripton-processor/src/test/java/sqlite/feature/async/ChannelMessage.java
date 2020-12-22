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
package sqlite.feature.async;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.annotation.BindType;



/**
 * The Class ChannelMessage.
 */
@BindType
public class ChannelMessage extends UidEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2411765210163916759L;

	/** The id. */
	@BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
	protected long id;

	/** The image. */
	protected byte[] image;

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image            the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/** The day. */
	protected DayType day;

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public DayType getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day            the day to set
	 */
	public void setDay(DayType day) {
		this.day = day;
	}

	/** The owner uid. */
	protected String ownerUid;

	/** The text. */
	protected String text;

	/** The type. */
	protected String type;

	/** The update time. */
	protected long updateTime;

	/** The valid. */
	protected boolean valid;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the owner uid.
	 *
	 * @return the owner uid
	 */
	public String getOwnerUid() {
		return ownerUid;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the update time.
	 *
	 * @return the update time
	 */
	public long getUpdateTime() {
		return updateTime;
	}

	/**
	 * Checks if is valid.
	 *
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Sets the id.
	 *
	 * @param id            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Sets the owner uid.
	 *
	 * @param ownerUid the new owner uid
	 */
	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Sets the update time.
	 *
	 * @param updateTime the new update time
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * Sets the valid.
	 *
	 * @param valid            the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChannelMessage [text=" + text + ", type=" + type + ", ownerUid=" + ownerUid + ", updateTime=" + updateTime + "]";
	}

}
