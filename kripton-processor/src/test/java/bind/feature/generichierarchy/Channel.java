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
package bind.feature.generichierarchy;

import com.abubusoft.kripton.annotation.BindType;



/**
 * The Class Channel.
 */
@BindType
public class Channel extends UIDObject {	

	/* (non-Javadoc)
	 * @see bind.feature.generichierarchy.UIDObject#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (imageSize ^ (imageSize >>> 32));
		result = prime * result + ((imageType == null) ? 0 : imageType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Channel other = (Channel) obj;
		if (imageSize != other.imageSize)
			return false;
		if (imageType == null) {
			if (other.imageType != null)
				return false;
		} else if (!imageType.equals(other.imageType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5414896193118986093L;

	/** The image type. */
	protected String imageType;
	
	/** The image size. */
	protected long imageSize;

	/**
	 * Gets the image size.
	 *
	 * @return the image size
	 */
	public long getImageSize() {
		return imageSize;
	}

	/**
	 * Sets the image size.
	 *
	 * @param imageSize the new image size
	 */
	public void setImageSize(long imageSize) {
		this.imageSize = imageSize;
	}

	/** The name. */
	protected String name;
	
	/**
	 * Gets the image type.
	 *
	 * @return the image type
	 */
	public String getImageType() {
		return imageType;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the image type.
	 *
	 * @param imageType the new image type
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	/* (non-Javadoc)
	 * @see bind.feature.generichierarchy.UIDObject#toString()
	 */
	@Override
	public String toString() {
		return "Channel [imageType=" + imageType + ", imageSize=" + imageSize + ", typeName=" + name + ", uid=" + uid + "]";
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Checks for image.
	 *
	 * @return true, if successful
	 */
	public boolean hasImage()
	{
		return this.imageSize>0;
	}
}
