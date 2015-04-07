package kripton22;

import java.util.logging.Logger;

import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.database.DatabaseSchemaOptions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArgonSQLiteHelper extends SQLiteOpenHelper {
	
	Logger logger=Logger.getGlobal();
	SQLiteSchema databaseSchema;

	public ArgonSQLiteHelper(Context context, String databaseName, int version) {
		super(context, databaseName, null, version);
		
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(Bean01.class);

		databaseSchema = SQLiteSchema.build(databaseName, options);
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
		logger.warning("Upgrading database from version " + oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		//db.execSQL("DROP TABLE IF EXISTS " + ChatMessageTable.TABLE_NAME);
		String[] sql=databaseSchema.dropTablesSQL();
		for (int i=0;i <sql.length;i++)
		{
			logger.info(sql[i]);
			db.execSQL(sql[i]);	
		}
		
		onCreate(db);
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

}