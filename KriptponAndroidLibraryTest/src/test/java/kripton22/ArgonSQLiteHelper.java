package kripton22;

import java.util.ArrayList;

import org.robolectric.util.Logger;

import com.abubusoft.kripton.android.SQLiteInsert;
import com.abubusoft.kripton.android.SQLiteQuery;
import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.android.SQLiteUpdate;
import com.abubusoft.kripton.database.DatabaseSchemaFactory;
import com.abubusoft.kripton.database.DatabaseSchemaOptions;
import com.abubusoft.kripton.database.NameConverterType;
import com.abubusoft.kripton.database.QueryListener;
import com.abubusoft.kripton.database.QueryOptions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArgonSQLiteHelper extends SQLiteOpenHelper {
	
	SQLiteSchema databaseSchema;
	
	SQLiteQuery selectAll;

	SQLiteQuery selectOrder01;
	
	SQLiteInsert insert;

	SQLiteQuery selectWhere01;

	SQLiteUpdate update;
	
	SQLiteQuery selectById;
	
	public ArgonSQLiteHelper(Context context, String databaseName, int version) {
		super(context, databaseName, null, version);
		
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.nameConverter(NameConverterType.UPPER_UNDERSCORE);
		options.tablePrefix("TD_");
	//	options.add(Bean01.class);
		options.add(ChatMessage.class);

		databaseSchema = DatabaseSchemaFactory.create(databaseName, SQLiteSchema.class, options);
		
		selectAll=databaseSchema.createQuery(ChatMessage.class, QueryOptions.build());
		
		insert=databaseSchema.getInsert(ChatMessage.class);
		selectById=databaseSchema.getQuery(ChatMessage.class, "defaultById");
		selectOrder01=databaseSchema.createQuery(ChatMessage.class, QueryOptions.build().name("01").order("latitude ASC"));
		selectWhere01=databaseSchema.createQuery(ChatMessage.class, QueryOptions.build().name("02").select("id, creationTimestamp").where(" latitude>#{latitude} ").paramsClass(Param02.class));
		update=databaseSchema.getUpdate(ChatMessage.class);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String[] sql=databaseSchema.createTablesSQL();
		
		for (int i=0;i <sql.length;i++)
		{
			String temp=sql[i];
			database.execSQL(temp);	
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String[] sql=databaseSchema.dropTablesSQL();
		for (int i=0;i <sql.length;i++)
		{
			db.execSQL(sql[i]);	
		}
		
		onCreate(db);
	}
	
	public <E> ArrayList<E> selectAll(SQLiteDatabase database, Class<E> clazz)
	{
		System.out.println(selectAll.getSQL());
		return selectAll.execute(database, clazz);
	}

	public boolean insert(SQLiteDatabase database, ChatMessage bean) {
		System.out.println(insert.getSQL());
		return insert.execute(database, bean);
	}

	public ArrayList<ChatMessage> selectOrder01(SQLiteDatabase database, Class<ChatMessage> class1) {
		return selectOrder01.execute(database, class1);
	}

	public void selectOrder01(SQLiteDatabase database, Class<ChatMessage> class1, QueryListener<ChatMessage> queryListener) {
		selectOrder01.executeWithListener(database, class1, queryListener);
	}
	
	public void selectWhere01(SQLiteDatabase database, Class<ChatMessage> class1, Param02 params, QueryListener<ChatMessage> queryListener) {
		selectWhere01.executeWithListener(database, class1,params, queryListener);
	}

	public int update(SQLiteDatabase database, ChatMessage bean) {
		System.out.println(update.getSQL());
		return update.execute(database, bean, null);
	}

	public ChatMessage select(SQLiteDatabase database, Class<ChatMessage> clazz, long id) {
		System.out.println(selectById.getSQL());			
		return selectById.executeOne(database,clazz,id);
	}

}