package kripton22;

import java.util.Date;

import org.junit.Test;


import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.android.SQLiteHandler;
import com.abubusoft.kripton.android.SQLiteInsert;
import com.abubusoft.kripton.android.SQLiteQuery;
import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.annotation.BindQueryParams;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.database.DatabaseColumn;
import com.abubusoft.kripton.database.DatabaseSchemaOptions;
import com.abubusoft.kripton.database.DatabaseTable;
import com.abubusoft.kripton.database.InsertOptions;
import com.abubusoft.kripton.database.ParametrizedString;
import com.abubusoft.kripton.database.QueryOptions;
import com.abubusoft.kripton.database.SQLStatement;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

import issue.BaseTest;

public class Test01Test extends BaseTest {

	
	@BindQueryParams
	public static class Params {
		public int value1;
		
		public Date value2;
		
		public String value3;
	} 
	
	@Test
	public void test02() {
		String input = " id = #{id	}, name=#{  name} and love=#{name};";
		logger.info("input  " + input);
		
		ParametrizedString result = SQLiteHandler.splitParams(input);
		logger.info("output " + result.value);
		
		int i=0;
		for (String paramName: result.params)
		{
			logger.info("param "+i+" " + paramName);
			i++;
		}
	}

	@Test
	public void test01() throws MappingException, WriterException {
		Bean01 bean = new Bean01();

		BinderWriter writer = BinderFactory.getXMLWriter(BinderOptions.build().indent(true));

		MappingSchema mappingSchema = MappingSchema.fromClass(Bean01.class);

		logger.info("\n" + writer.write(mappingSchema));

		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(Bean01.class);

		SQLiteSchema databaseSchema = SQLiteSchema.build("prova", options);

		databaseSchema.createQuery(Bean01.class, QueryOptions.build().select("name,\n id"));
		databaseSchema.createQuery(Bean01.class, QueryOptions.build().select("name").where("name=#{name} and love=#{name}"));

		for (DatabaseTable table : databaseSchema.getTables().values()) {
			logger.info("table " + table.name + " java-name: " + table.schema.tableInfo.name);

			for (DatabaseColumn column : table.columns) {
				logger.info("\tcolumn-name " + column.name + " feature: " + column.feature + " field-name: " + column.schema.getName() + " java-type: "
						+ column.schema.getFieldType() + " db-type: " + column.type);
			}

			for (SQLStatement item : table.queries.values()) {
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
		
		SQLStatement query = databaseSchema.createQuery(Bean01.class, QueryOptions.build().select("name").where("name=#{name} and love=#{name}").paramsClass(Params.class));

		//SQLiteDatabase db = SQLiteDatabase.openDatabase("", null, 0);
		// db.ra

	}
	
	@Test
	public void test03() throws MappingException, WriterException {
		Bean01 bean = new Bean01();

		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(Bean01.class);

		SQLiteSchema databaseSchema = SQLiteSchema.build("prova", options);
		
		SQLiteQuery query = databaseSchema.createQuery(Bean01.class, QueryOptions.build().select("name").where("name=#{name} and love=#{name}").paramsClass(Params.class));		
		
		logger.info(""+query.columns.length);
	}
	
	@BindQueryParams
	public static class P{
		String uid;
		
		float latitude;
	}
	
	@Test
	public void testSelect() throws MappingException, WriterException {
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(ChatMessage.class);

		SQLiteSchema databaseSchema = SQLiteSchema.build("prova", options);
		SQLiteQuery query = databaseSchema.createQuery(ChatMessage.class, QueryOptions.build().name("prova").where("uid=#{uid} and latitude=#{latitude} and latitude=#{latitude}").paramsClass(P.class));
		logger.info(""+query.getSQL());
		P params=new P();
		params.uid="xxx";
		params.latitude=12.0f;
		
		String[] p = query.getParams(params);
		for (String item: p)
		{
			logger.info("parameter: "+item);
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
		
		databaseSchema.select(null, ChatMessage.class,"prova", p);
	}
	
	@Test
	public void testInsert() throws MappingException, WriterException {
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(ChatMessage.class);

		SQLiteSchema databaseSchema = SQLiteSchema.build("prova", options);
		SQLiteInsert insert = databaseSchema.createInsert(ChatMessage.class, InsertOptions.build());
		logger.info(""+insert.getSQL());
		
		ChatMessage bean=new ChatMessage();
		
		bean.creationTimestamp=(new Date()).getTime();
		bean.value="cuiao balelo";
		databaseSchema.insert(null, bean);
		
		
//		P params=new P();
//		params.uid="xxx";
//		params.latitude=12.0f;
//		
//		String[] p = query.getParams(params);
//		for (String item: p)
//		{
//			logger.info("parameter: "+item);
//		}
//		
//		{
//			String[] createSql = databaseSchema.createTablesSQL();
//			for (int i = 0; i < createSql.length; i++) {
//				logger.info(createSql[i]);
//			}
//		}
//
//		{
//			String[] createSql = databaseSchema.dropTablesSQL();
//			for (int i = 0; i < createSql.length; i++) {
//				logger.info(createSql[i]);
//			}
//		}				
	}
}
