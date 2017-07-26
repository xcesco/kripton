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
package bind.feat.generichierarchy.case1.model;

import com.abubusoft.kripton.annotation.BindType;

import bind.generichierarchy.case1.model.Message;
import bind.generichierarchy.case1.model.MessageType;
import bind.generichierarchy.case1.model.UIDObject;

@BindType
public class Message extends UIDObject {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((channelUid == null) ? 0 : channelUid.hashCode());
		result = prime * result + ((faceUid == null) ? 0 : faceUid.hashCode());
		result = prime * result + ((ownerUid == null) ? 0 : ownerUid.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (channelUid == null) {
			if (other.channelUid != null)
				return false;
		} else if (!channelUid.equals(other.channelUid))
			return false;
		if (faceUid == null) {
			if (other.faceUid != null)
				return false;
		} else if (!faceUid.equals(other.faceUid))
			return false;
		if (ownerUid == null) {
			if (other.ownerUid != null)
				return false;
		} else if (!ownerUid.equals(other.ownerUid))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	private static final long serialVersionUID = -2411765210163916759L;
	
	protected String faceUid;

	protected String text;
	
	protected MessageType type;

	protected String ownerUid;
	
	protected String channelUid;

	public String getFaceUid() {
		return faceUid;
	}

	public void setFaceUid(String faceUid) {
		this.faceUid = faceUid;
	}

	public String getChannelUid() {
		return channelUid;
	}

	public void setChannelUid(String channelUid) {
		this.channelUid = channelUid;
	}

	public String getOwnerUid() {
		return ownerUid;
	}

	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}


	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [faceUid=" + faceUid + ", text=" + text + ", type=" + type + ", ownerUid=" + ownerUid
				+ ", channelUid=" + channelUid + ", uid=" + uid + ", updateTime=" + updateTime + "]";
	}

	public boolean hasFace() {
		return faceUid!=null;
	}




}
