package com.abubusoft.kripton.processor.sqlite.grammars.uri;

public class UriPlaceHolder {
	
	public String value;
	
	public boolean composed;
	
	public UriPlaceHolder(String value) {
		this.value=value;
		this.composed=value.indexOf(".")>=0;	
	}
	
}
