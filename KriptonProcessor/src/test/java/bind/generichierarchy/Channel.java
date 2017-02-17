package bind.generichierarchy;

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
