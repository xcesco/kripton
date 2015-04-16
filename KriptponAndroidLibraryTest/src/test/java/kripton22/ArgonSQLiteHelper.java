package kripton22;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.abubusoft.kripton.DatabaseSchemaFactory;
import com.abubusoft.kripton.DatabaseSchemaOptions;
import com.abubusoft.kripton.android.SQLiteInsert;
import com.abubusoft.kripton.android.SQLiteQuery;
import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.android.SQLiteUpdate;
import com.abubusoft.kripton.binder.database.NameConverterType;
import com.abubusoft.kripton.binder.database.QueryListener;
import com.abubusoft.kripton.binder.database.QueryOptions;

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

	public boolean insert(SQLiteDatabase database, BeanTest1_0 bean) {
		System.out.println(insert.getSQL());
		return insert.execute(database, bean);
	}

	public ArrayList<BeanTest1_0> selectOrder01(SQLiteDatabase database, Class<BeanTest1_0> class1) {
		return selectOrder01.execute(database, class1);
	}

	public void selectOrder01(SQLiteDatabase database, Class<BeanTest1_0> class1, QueryListener<BeanTest1_0> queryListener) {
		selectOrder01.executeWithListener(database, class1, queryListener);
	}
	
	public void selectWhere01(SQLiteDatabase database, Class<BeanTest1_0> class1, Param02 params, QueryListener<BeanTest1_0> queryListener) {
		selectWhere01.executeWithListener(database, class1,params, queryListener);
	}

	public int update(SQLiteDatabase database, BeanTest1_0 bean) {
		System.out.println(update.getSQL());
		return update.execute(database, bean, null);
	}

	public BeanTest1_0 select(SQLiteDatabase database, Class<BeanTest1_0> clazz, long id) {
		System.out.println(selectById.getSQL());			
		return selectById.executeOne(database,clazz,id);
	}

}