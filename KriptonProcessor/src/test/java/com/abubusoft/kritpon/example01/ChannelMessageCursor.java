package com.abubusoft.kritpon.example01;

import java.util.List;

import android.database.Cursor;

public class ChannelMessageCursor {
	
	public interface OnChannelMessageListener
	{
		void onRow(int rowCount, ChannelMessageCursor bean);
	}
	
	public static ChannelMessageCursor create(Cursor cursor)
	{
		return new ChannelMessageCursor(cursor);
	}

	protected Cursor cursor;
	
	public ChannelMessageCursor(Cursor value) {
		cursor=value;
				
	}

	public List<ChannelMessageCursor> execute()
	{
		return null;
	}
	
	public void execute(OnChannelMessageListener listener)
	{
		
	}
	
	public void wrap(Cursor cursor)
	{
		this.cursor=cursor;
		
		int index0=cursor.getColumnIndex("a");
		
		if (index0>-1 && cursor.isNull(index0)) cursor.getDouble(index0);
		
		
	}
	
	
	
}
