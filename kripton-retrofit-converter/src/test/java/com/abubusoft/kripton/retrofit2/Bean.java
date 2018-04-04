package com.abubusoft.kripton.retrofit2;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean {
	
	public Bean()
	{
		
	}

	public Bean(String uid, String description) {
		super();
		this.uid = uid;
		this.description = description;
	}

	public String uid;
	
	public String description;
}
