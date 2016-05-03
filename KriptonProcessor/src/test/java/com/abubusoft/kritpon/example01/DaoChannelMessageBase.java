package com.abubusoft.kritpon.example01;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DaoChannelMessageBase /*implements DaoChannelMessage*/ {

	SQLiteDatabase database;

	public long insertContactA(String ownerUid, String text, long updateTime) {
		ContentValues contentValues = new ContentValues();		
		contentValues.put("owner_uid", ownerUid);
		contentValues.put("text", text);
		contentValues.put("update_time", updateTime);		
		long result = database.insert("contacts", null, contentValues);
						
		return result;
	}
	
	  public String insertContact(String ownerUid, String text, long updateTime) {
		    ContentValues contentValues=new ContentValues();
		    contentValues.put("owner_uid", ownerUid);
		    contentValues.put("text", text);
		    contentValues.put("update_time", updateTime);

		    long result = database.insert("channel_message", null, contentValues);
		    return String.valueOf(result);
		  }

	public long insertContact(ChannelMessage bean) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", bean.getOwnerUid());
		contentValues.put("phone", bean.getText());
		contentValues.put("email", bean.getOwnerUid());
		long result = database.insert("contacts", null, contentValues);

		return result;
	}

	public long updateContact(ChannelMessage bean, long id) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", bean.getOwnerUid());
		contentValues.put("phone", bean.getText());
		contentValues.put("email", bean.getOwnerUid());
		
		String[] whereConditions={String.valueOf(id)};
		
		long result = database.update("contacts", contentValues, "where id=?", whereConditions);
		return result;
	}
	
	public int deleteContact(ChannelMessage bean, long id) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", bean.getOwnerUid());
		contentValues.put("phone", bean.getText());
		contentValues.put("email", bean.getOwnerUid());
		
		String[] whereConditions={String.valueOf(id)};
		
		int result = database.delete("contacts", "where id=?", whereConditions);
		return result;
	}

	public ChannelMessage selectBean() {
		// TODO Auto-generated method stub
		return null;
	}
}
