package com.abubusoft.kripton.android.kripton15;

import java.io.Serializable;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Level1 implements Serializable {

	private static final long serialVersionUID = 2195822642540109939L;

	public Level2 info;
	
	public String name;
}
