/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.example01;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.sqlite.FieldType;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class ChannelMessage extends UidEntity {

	private static final long serialVersionUID = -2411765210163916759L;
	
	@BindColumn(columnType = ColumnType.PRIMARY_KEY)
	protected long id;
	
	protected byte[] image;
	
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	@BindColumn(fieldType=FieldType.ENUM)
	protected DayType day;
	
	/**
	 * @return the day
	 */
	public DayType getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(DayType day) {
		this.day = day;
	}

	protected String ownerUid;

	protected String text;

	protected String type;

	protected long updateTime;

	protected boolean valid;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	public String getOwnerUid() {
		return ownerUid;
	}

	public String getText() {
		return text;
	}

	public String getType() {
		return type;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "ChannelMessage [text=" + text + ", type=" + type + ", ownerUid=" + ownerUid + ", updateTime=" + updateTime
				+ "]";
	}




}
