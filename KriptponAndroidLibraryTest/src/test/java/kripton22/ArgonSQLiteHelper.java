package kripton22;

import java.util.ArrayList;

import com.abubusoft.kripton.android.SQLiteInsert;
import com.abubusoft.kripton.android.SQLiteQuery;
import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.android.SQLiteUpdate;
import com.abubusoft.kripton.database.DatabaseSchemaFactory;
import com.abubusoft.kripton.database.DatabaseSchemaOptions;
import com.abubusoft.kripton.database.InsertOptions;
import com.abubusoft.kripton.database.NameConverterType;
import com.abubusoft.kripton.database.QueryListener;
import com.abubusoft.kripton.database.QueryOptions;
import com.abubusoft.kripton.database.UpdateOptions;

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
	
	public ArgonSQLiteHelper(Context context, String databaseName, int version) {
		super(context, databaseName, null, version);
		
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.nameConverter(NameConverterType.UPPER_UNDERSCORE);
		options.tablePrefix("TD_");
	//	options.add(Bean01.class);
		options.add(ChatMessage.class);

		databaseSchema = DatabaseSchemaFactory.create(databaseName, SQLiteSchema.class, options);
		
		selectAll=databaseSchema.createQuery(ChatMessage.class, QueryOptions.build());
		insert=databaseSchema.createInsert(ChatMessage.class, InsertOptions.build());
		selectOrder01=databaseSchema.createQuery(ChatMessage.class, QueryOptions.build().name("01").order("latitude ASC"));
		selectWhere01=databaseSchema.createQuery(ChatMessage.class, QueryOptions.build().name("02").select("id, creationTimestamp").where(" latitude>#{latitude} ").paramsClass(Param02.class));
		update=databaseSchema.getUpdate(ChatMessage.class,"*");
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
		return selectAll.execute(database, clazz);
	}
/*
	public <E extends ArgonSQLEntity, T extends ArgonSQLTable<E>> void select(SQLiteDatabase database, T table, SQLSelectOptions options,
			ArgonSQLReadEntityListener<E> listener) {
		Cursor cursor = null;
		if (options != null) {
			cursor = database.query(table.getName(), table.getAllColumns(), options.whereCondition, options.whereParamaters, null, null, options.orderBy, null);
		} else {
			cursor = database.query(table.getName(), table.getAllColumns(), null, null, null, null, null, null);
		}

		E entity = table.createNew();

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				listener.onRead(table.fromCursor(entity, cursor));
				table.reset(entity);
			} while (cursor.moveToNext());
		} else {
			logger.info("Non trovato");
		}

		cursor.close();

	}*/
/*
	public <E extends ArgonSQLEntity, T extends ArgonSQLTable<E>> boolean insert(SQLiteDatabase database, T table, E entity) {
		ContentValues values = table.toContent(entity);
		long id = database.insert(table.getName(), null, values);
		entity.id = id;

		return entity.id != -1;
	}

	public <E extends ArgonSQLEntity, T extends ArgonSQLTable<E>> int updateWhere(SQLiteDatabase database, T table, E entity, String whereConditions) {
		ContentValues values = table.toContent(entity);
		return database.update(table.getName(), values, whereConditions, null);
	}

	public <E extends ArgonSQLEntity, T extends ArgonSQLTable<E>> int updateWhere(SQLiteDatabase database, T table, ContentValues values, String whereConditions) {
		return database.update(table.getName(), values, whereConditions, null);
	}

	public <E extends ArgonSQLEntity, T extends ArgonSQLTable<E>> boolean updateById(SQLiteDatabase database, T table, E entity) {
		ContentValues values = table.toContent(entity);
		return database.update(table.getName(), values, " _id='" + entity.id + "'", null) == 1;
	}

	public <E extends ArgonSQLEntity, T extends ArgonSQLTable<E>> boolean deleteById(SQLiteDatabase database, T table, E entity) {
		return database.delete(table.getName(), " _id='" + entity.id + "'", null) == 1;
	}*/

	public boolean insert(SQLiteDatabase database, ChatMessage bean) {
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

	public boolean update(SQLiteDatabase database, ChatMessage bean) {
		return update.execute(database, bean, null);
	}
	
	//public boolean updateById(SQLiteDatabase database, ChatMessage bean) {
	//	return databaseSchema.update(database, bean);
	//}

}