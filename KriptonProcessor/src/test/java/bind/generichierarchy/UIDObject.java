package bind.generichierarchy;

import java.io.Serializable;

/**
 * Classe che rappresenta un oggetto identificabile da un UID.
 * 
 * @author xcesco
 *
 */
public class UIDObject implements Serializable {

	private static final long serialVersionUID = -5883323667838359782L;

	protected String uid;

	protected long updateTime;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UIDObject other = (UIDObject) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (updateTime != other.updateTime)
			return false;
		return true;
	}

	public String getUid() {
		return uid;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + (int) (updateTime ^ (updateTime >>> 32));
		return result;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UIDObject [uid=" + uid + ", updateTime=" + updateTime + "]";
	}
}
