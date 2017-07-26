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
package bind.feat.generichierarchy;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Channel extends UIDObject {	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (imageSize ^ (imageSize >>> 32));
		result = prime * result + ((imageType == null) ? 0 : imageType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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

	private static final long serialVersionUID = -5414896193118986093L;

	protected String imageType;
	
	protected long imageSize;

	public long getImageSize() {
		return imageSize;
	}

	public void setImageSize(long imageSize) {
		this.imageSize = imageSize;
	}

	protected String name;
	
	public String getImageType() {
		return imageType;
	}

	public String getName() {
		return name;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public String toString() {
		return "Channel [imageType=" + imageType + ", imageSize=" + imageSize + ", typeName=" + name + ", uid=" + uid + "]";
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean hasImage()
	{
		return this.imageSize>0;
	}
}
