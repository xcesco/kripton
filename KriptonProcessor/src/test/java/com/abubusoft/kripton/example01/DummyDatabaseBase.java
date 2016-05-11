package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.android.KriptonLibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DummyDatabaseBase extends SQLiteOpenHelper {
	
	public static DummyDatabaseBase instance;
	
	protected final static String name="dummy";
	
	protected final static int version=1;
	
	
	public DummyDatabaseBase instance()
	{
		if (instance==null)
		{
			instance=new DummyDatabaseBase(KriptonLibrary.context);
		}
		
		return instance;
	}
	
	
	private DummyDatabaseBase(Context context)
	{
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		//database.execSQL(ChannelMessageTable.CREATE_TABLE_SQL);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
