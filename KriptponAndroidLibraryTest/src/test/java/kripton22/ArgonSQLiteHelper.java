package kripton22;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;

public class ArgonSQLiteHelper extends SQLiteOpenHelper {
	
	SQLiteSchema databaseSchema;
	
	SQLiteQuery<BeanTest1_0> selectAll;

	SQLiteQuery<BeanTest1_0> selectOrder01;
	
	SQLiteInsert insert;

	SQLiteQuery<BeanTest1_0> selectWhere01;

	SQLiteUpdate update;
	
	SQLiteQuery<BeanTest1_0> selectById;
	
	public ArgonSQLiteHelper(Context context, String databaseName, int version) {
		super(context, databaseName, null, version);
		
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.nameConverter(NameConverterType.UPPER_UNDERSCORE);
		options.tablePrefix("TD_");
	//	options.add(Bean01.class);
		options.add(BeanTest1_0.class);

		databaseSchema = DatabaseSchemaFactory.create(databaseName, SQLiteSchema.class, options);
		
		selectAll=databaseSchema.createQuery(BeanTest1_0.class, QueryOptions.build());
		
		insert=databaseSchema.getInsert(BeanTest1_0.class);
		selectById=databaseSchema.getQuery(BeanTest1_0.class, "defaultById");
		selectOrder01=databaseSchema.createQuery(BeanTest1_0.class, QueryOptions.build().name("01").order("latitude ASC"));
		selectWhere01=databaseSchema.createQuery(BeanTest1_0.class, QueryOptions.build().name("02").select("id, creationTimestamp").where(" latitude>#{latitude} ").paramsClass(Param02.class));
		update=databaseSchema.getUpdate(BeanTest1_0.class);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		ArrayList<String> sql=databaseSchema.createTablesSQL();
		
		for (int i=0;i <sql.size();i++)
		{
			String temp=sql.get(i);
			database.execSQL(temp);	
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		ArrayList<String> sql=databaseSchema.dropTablesSQL();
		for (int i=0;i <sql.size();i++)
		{
			db.execSQL(sql.get(i));	
		}
		
		onCreate(db);
	}
	
	public ArrayList<BeanTest1_0> selectAll(SQLiteDatabase database)
	{
		System.out.println(selectAll.getSQL());
		
		return selectAll.executeList(database);
	}

	public boolean insert(SQLiteDatabase database, BeanTest1_0 bean) {
		System.out.println(insert.getSQL());
		return insert.execute(database, bean);
	}

	public ArrayList<BeanTest1_0> selectOrder01(SQLiteDatabase database) {
		return selectOrder01.executeList(database);
	}

	public void selectOrder01(SQLiteDatabase database, Class<BeanTest1_0> class1) {
		selectOrder01.executeWithListener(database, class1);
	}
	
	public void selectWhere01(SQLiteDatabase database, Param02 params) {
		selectWhere01.executeWithListener(database, params);
	}

	public int update(SQLiteDatabase database, BeanTest1_0 bean) {
		System.out.println(update.getSQL());
		return update.execute(database, bean, null);
	}

	public BeanTest1_0 select(SQLiteDatabase database, long id) {
		System.out.println(selectById.getSQL());			
		return selectById.executeOne(database,id);
	}

}