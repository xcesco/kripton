/**
 * 
 */
package com.abubusoft.kripton.common;

/**
 * @author xcesco
 *
 */
public class Pair<A, B> {
	public Pair()
	{
		
	}
	
	public Pair(A value0, B value1)
	{
		this.value0=value0;
		this.value1=value1;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value0 == null) ? 0 : value0.hashCode());
		result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (value0 == null) {
			if (other.value0 != null)
				return false;
		} else if (!value0.equals(other.value0))
			return false;
		if (value1 == null) {
			if (other.value1 != null)
				return false;
		} else if (!value1.equals(other.value1))
			return false;
		return true;
	}

	public A value0;
	
	public B value1;
}
