package com.abubusoft.kripton.android;
/**
 * 
 */


import java.util.ArrayList;

import com.abubusoft.kripton.binder.annotation.BindElement;
import com.abubusoft.kripton.binder.annotation.BindRoot;


/**
 * @author Cesco
 *
 */
@BindRoot
public class ChatMessageArray {

	@BindElement
	ArrayList<ChatMessage> array=new ArrayList<>();
			
	public void add(ChatMessage message)
	{
		array.add(message);		
	}
	
	public ChatMessage get(int index)
	{
		return array.get(index);	
	}
	
	public String getLastUid()
	{
		if (array.size()==0) return null;
		return array.get(array.size()-1).uid;
	}
	
	public int size()
	{
		return array.size();	
	}
	
	public void clear()
	{
		array.clear();	
	}
	
}
