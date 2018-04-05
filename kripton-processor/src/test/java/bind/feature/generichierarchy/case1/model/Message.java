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
package bind.feature.generichierarchy.case1.model;

import com.abubusoft.kripton.annotation.BindType;

import bind.feature.generichierarchy.UIDObject;


// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
@BindType
public class Message extends UIDObject {

	/* (non-Javadoc)
	 * @see bind.feature.generichierarchy.UIDObject#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see bind.feature.generichierarchy.UIDObject#equals(java.lang.Object)
	 */
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

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2411765210163916759L;
	
	/** The face uid. */
	protected String faceUid;

	/** The text. */
	protected String text;
	
	/** The type. */
	protected MessageType type;

	/** The owner uid. */
	protected String ownerUid;
	
	/** The channel uid. */
	protected String channelUid;

	/**
	 * Gets the face uid.
	 *
	 * @return the face uid
	 */
	public String getFaceUid() {
		return faceUid;
	}

	/**
	 * Sets the face uid.
	 *
	 * @param faceUid the new face uid
	 */
	public void setFaceUid(String faceUid) {
		this.faceUid = faceUid;
	}

	/**
	 * Gets the channel uid.
	 *
	 * @return the channel uid
	 */
	public String getChannelUid() {
		return channelUid;
	}

	/**
	 * Sets the channel uid.
	 *
	 * @param channelUid the new channel uid
	 */
	public void setChannelUid(String channelUid) {
		this.channelUid = channelUid;
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
	 * Sets the owner uid.
	 *
	 * @param ownerUid the new owner uid
	 */
	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}


	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public MessageType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(MessageType type) {
		this.type = type;
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
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see bind.feature.generichierarchy.UIDObject#toString()
	 */
	@Override
	public String toString() {
		return "Message [faceUid=" + faceUid + ", text=" + text + ", type=" + type + ", ownerUid=" + ownerUid
				+ ", channelUid=" + channelUid + ", uid=" + uid + ", updateTime=" + updateTime + "]";
	}

	/**
	 * Checks for face.
	 *
	 * @return true, if successful
	 */
	public boolean hasFace() {
		return faceUid!=null;
	}




}
