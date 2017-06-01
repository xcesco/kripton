package com.abubusoft.kripton.processor.sqlite.grammars.uri;

public class UriPlaceHolder {
	
	@Override
	public String toString() {
		return "UriPlaceHolder [pathSegmentIndex=" + pathSegmentIndex + ", value=" + value + ", composed=" + composed
				+ "]";
	}

	public int pathSegmentIndex;
	
	public String value;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (composed ? 1231 : 1237);
		result = prime * result + pathSegmentIndex;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UriPlaceHolder other = (UriPlaceHolder) obj;
		if (composed != other.composed)
			return false;
		if (pathSegmentIndex != other.pathSegmentIndex)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public boolean composed;
	
	public UriPlaceHolder(int pathIndex, String value) {
		this.pathSegmentIndex=pathIndex;
		this.value=value;
		this.composed=value.indexOf(".")>=0;	
	}
	
}
