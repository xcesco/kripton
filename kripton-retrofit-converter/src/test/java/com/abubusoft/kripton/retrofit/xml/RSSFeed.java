package com.abubusoft.kripton.retrofit.xml;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType(value="rss")
public class RSSFeed {
	
	public List<Channel> channels;
}