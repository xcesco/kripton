package com.abubusoft.kritpon.example01;

import com.abubusoft.kripton.android.KriptonLibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DummyDatabase extends SQLiteOpenHelper {
	
	public static DummyDatabase instance;
	
	protected final static String name="dummy";
	
	protected final static int version=1;
	
	
	public DummyDatabase instance()
	{
		if (instance==null)
		{
			instance=new DummyDatabase(KriptonLibrary.context);
		}
		
		return instance;
	}
	
	
	private DummyDatabase(Context context)
	{
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		//database.execSQL();		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
