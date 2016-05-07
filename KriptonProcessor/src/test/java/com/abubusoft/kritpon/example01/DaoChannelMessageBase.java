package com.abubusoft.kritpon.example01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.abubusoft.kripton.processor.sqlite.transform.IntegerTransform;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DaoChannelMessageBase /* implements DaoChannelMessage */{

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
		ContentValues contentValues = new ContentValues();
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

		String[] whereConditions = { String.valueOf(id) };

		long result = database.update("contacts", contentValues, "where id=?", whereConditions);
		return result;
	}

	public int deleteContact(ChannelMessage bean, long id) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", bean.getOwnerUid());
		contentValues.put("phone", bean.getText());
		contentValues.put("email", bean.getOwnerUid());

		String[] whereConditions = { String.valueOf(id) };

		int result = database.delete("contacts", "where id=?", whereConditions);
		return result;
	}

	/**
	 * <p>
	 * Projected column are:
	 * </p>
	 *
	 * <pre>
	 * [id, owner_uid, text, type, update_time, valid]
	 * </pre>
	 *
	 * <p>
	 * Query used with this SQLSelect is:
	 * </p>
	 *
	 * <pre>
	 * select distinct id, owner_uid, text, type, update_time, valid from channel_message where id = ${bean.valid} having uid = ${uid}
	 * </pre>
	 *
	 * <p>
	 * Its parameters are:
	 * </p>
	 *
	 * <pre>
	 * [bean.valid, uid]
	 * </pre>
	 *
	 * @param bean
	 * @param uid
	 *            caio2
	 */
	public List<ChannelMessage> selectList(ChannelMessage bean, long uid) {
	    String[] args={String.valueOf(bean.isValid()), String.valueOf(uid)};
	    Cursor cursor = database.rawQuery("select distinct id, owner_uid, text, type, update_time, valid from channel_message where id = ? having uid = ? ", args);

	    LinkedList<ChannelMessage> resultList=new LinkedList<ChannelMessage>();
	    ChannelMessage resultBean=new ChannelMessage();
	    
	    
	    if (cursor.moveToFirst())
	    {
	    	int[] indexes={
	    	cursor.getColumnIndex("id"),
	    	cursor.getColumnIndex("owner_uid"),
	    	cursor.getColumnIndex("text"),
	    	cursor.getColumnIndex("type"),
	    	cursor.getColumnIndex("update_time"),
	    	cursor.getColumnIndex("valid")
	    	
	    	};
	    	
	    	do {
	    		resultBean=new ChannelMessage();	    			
	    		resultBean.setId(cursor.getInt(indexes[0]));
	    		resultBean.setOwnerUid(cursor.getString(indexes[1]));
	    		resultList.add(resultBean);
	    	} while (cursor.moveToNext());
	    }
	    
	    cursor.close();

	    return resultList;
	  }
}
