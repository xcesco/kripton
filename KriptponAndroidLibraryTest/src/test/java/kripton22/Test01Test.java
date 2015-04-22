package kripton22;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import all.BaseTest;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.DatabaseSchemaFactory;
import com.abubusoft.kripton.DatabaseSchemaOptions;
import com.abubusoft.kripton.android.SQLiteHandler;
import com.abubusoft.kripton.android.SQLiteHelper;
import com.abubusoft.kripton.android.SQLiteInsert;
import com.abubusoft.kripton.android.SQLiteQuery;
import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseTable;
import com.abubusoft.kripton.binder.database.Filter;
import com.abubusoft.kripton.binder.database.InsertOptions;
import com.abubusoft.kripton.binder.database.QueryOptions;
import com.abubusoft.kripton.binder.database.Statement;
import com.abubusoft.kripton.binder.database.helper.FilterHelper;
import com.abubusoft.kripton.binder.database.helper.SQLHelper;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

public class Test01Test extends BaseTest {

	
	public static class Params {
		public String name;
		
		public long id;
		
		public long uid;
		
	} 
	
	/*

	@Test
	public void test01() throws MappingException, WriterException {
		Bean01 bean = new Bean01();

		BinderWriter writer = BinderFactory.getXMLWriter(BinderOptions.build().indent(true));

		MappingSchema mappingSchema = MappingSchema.fromClass(Bean01.class);

		logger.info("\n" + writer.write(mappingSchema));

		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(Bean01.class);

		SQLiteSchema databaseSchema = DatabaseSchemaFactory.create("prova", SQLiteSchema.class, options);

		databaseSchema.createQuery(Bean01.class, QueryOptions.build().name("1").select("name,\n id"));
		databaseSchema.createQuery(Bean01.class, QueryOptions.build().name("2").select("name").where("name=#{name} and love=#{name}"));

		for (DatabaseTable table : databaseSchema.getTables().values()) {
			logger.info("table " + table.name + " java-name: " + table.schema.tableInfo.name);

			for (DatabaseColumn column : table.columns) {
				logger.info("\tcolumn-name " + column.name + " feature: " + column.feature + " field-name: " + column.schema.getName() + " java-type: "
						+ column.schema.getFieldType() + " db-type: " + column.type);
			}

			for (Statement item : table.queries.values()) {
				logger.info("\tset-name " + item.name + " set : " + item.columns.toString());
			}

		}

		{
			String[] createSql = databaseSchema.createTablesSQL();
			for (int i = 0; i < createSql.length; i++) {
				logger.info(createSql[i]);
			}
		}

		{
			String[] createSql = databaseSchema.dropTablesSQL();
			for (int i = 0; i < createSql.length; i++) {
				logger.info(createSql[i]);
			}
		}
		
		Statement query = databaseSchema.createQuery(Bean01.class, QueryOptions.build().name("3").select("name").where("name=#{name} and love=#{name}").paramsClass(Params.class));

		//SQLiteDatabase db = SQLiteDatabase.openDatabase("", null, 0);
		// db.ra

	}*/
	
	/*
	@Test
	public void test03() throws MappingException, WriterException {
		Bean01 bean = new Bean01();

		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(Bean01.class);

		SQLiteSchema databaseSchema = DatabaseSchemaFactory.create("prova", SQLiteSchema.class, options);
		
		SQLiteQuery query = databaseSchema.createQuery(Bean01.class, QueryOptions.build().select("name").where("name=#{id} and love=#{uid}").paramsClass(Params.class));		
		
		logger.info(""+query.columns.length);
	}*/
	
	public static class P{
		String uid;
		
		double latitude;
	}
	
	@Test
	public void testSelect() throws MappingException, WriterException {
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(BeanTest1_0.class);

		SQLiteSchema databaseSchema = DatabaseSchemaFactory.create("prova", SQLiteSchema.class, options);
		SQLiteQuery query = databaseSchema.createQuery(BeanTest1_0.class, QueryOptions.build().name("prova").where("uid=#{uid} and latitude=#{latitude} and latitude=#{latitude}").paramsClass(P.class));
		logger.info(""+query.getSQL());
		P params=new P();
		params.uid="xxx";
		params.latitude=12.0f;
		
		String[] p = query.getFilterValues(params);
		for (String item: p)
		{
			logger.info("parameter: "+item);
		}
		
		{
			ArrayList<String> createSql = databaseSchema.createTablesSQL();
			for (int i = 0; i < createSql.size(); i++) {
				logger.info(createSql.get(i));
			}
		}

		{
			ArrayList<String> createSql = databaseSchema.dropTablesSQL();
			for (int i = 0; i < createSql.size(); i++) {
				logger.info(createSql.get(i));
			}
		}	
		
	}
	
	/*
	@Test
	public void testInsert() throws MappingException, WriterException {
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(Bean0.class);

		SQLiteSchema databaseSchema = DatabaseSchemaFactory.create("prova", SQLiteSchema.class, options);
		SQLiteInsert insert = databaseSchema.createInsert(Bean0.class, InsertOptions.build());
		logger.info(""+insert.getSQL());
		
		Bean0 bean=new Bean0();
		
		bean.creationTimestamp=(new Date()).getTime();
		bean.value="cuiao balelo";
				
	}*/
}
