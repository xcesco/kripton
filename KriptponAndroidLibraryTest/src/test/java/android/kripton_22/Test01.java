package android.kripton_22;

import java.util.Date;

import org.junit.Test;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.Options;
import com.abubusoft.kripton.android.DatabaseType;
import com.abubusoft.kripton.android.SQLiteHandler;
import com.abubusoft.kripton.annotation.BindQueryParams;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.Query;
import com.abubusoft.kripton.binder.database.DatabaseSchema;
import com.abubusoft.kripton.binder.database.DatabaseSchemaOptions;
import com.abubusoft.kripton.binder.database.DatabaseTable;
import com.abubusoft.kripton.binder.database.ParametrizedString;
import com.abubusoft.kripton.binder.database.QueryOptions;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

import issue.BaseTest;

public class Test01 extends BaseTest {

	
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
		
		SQLiteHandler handler=new SQLiteHandler();
		
		ParametrizedString result = handler.splitParams(input);
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

		BinderWriter writer = BinderFactory.getXMLWriter(Options.build().indent(true));

		MappingSchema mappingSchema = MappingSchema.fromClass(Bean01.class);

		logger.info("\n" + writer.write(mappingSchema));

		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(Bean01.class);

		DatabaseSchema databaseSchema = DatabaseSchema.build("prova", DatabaseType.SQLITE, options);

		databaseSchema.createQuery(Bean01.class, QueryOptions.build().select("name,\n id"));
		databaseSchema.createQuery(Bean01.class, QueryOptions.build().select("name").where("name=#{name} and love=#{name}"));

		for (DatabaseTable table : databaseSchema.tables.values()) {
			logger.info("table " + table.name + " java-name: " + table.schema.tableInfo.name);

			for (DatabaseColumn column : table.columns) {
				logger.info("\tcolumn-name " + column.name + " feature: " + column.feature + " field-name: " + column.schema.getName() + " java-type: "
						+ column.schema.getFieldType() + " db-type: " + column.type);
			}

			for (Query item : table.queries.values()) {
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

		SQLiteDatabase db = SQLiteDatabase.openDatabase("", null, 0);
		// db.ra

	}
	
	@Test
	public void test03() throws MappingException, WriterException {
		Bean01 bean = new Bean01();

		BinderWriter writer = BinderFactory.getXMLWriter(Options.build().indent(true));

		MappingSchema mappingSchema = MappingSchema.fromClass(Bean01.class);

		logger.info("\n" + writer.write(mappingSchema));

		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(Bean01.class);

		DatabaseSchema databaseSchema = DatabaseSchema.build("prova", DatabaseType.SQLITE, options);
		
		Query query = databaseSchema.createQuery(Bean01.class, QueryOptions.build().select("name").where("name=#{name} and love=#{name}").paramsClass(Params.class));
		
		logger.info(""+query.columns.length);
	}
	
	@BindQueryParams
	public static class P{
		String uid;
	}
	
	@Test
	public void test04() throws MappingException, WriterException {
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(ChatMessage.class);

		DatabaseSchema databaseSchema = DatabaseSchema.build("prova", DatabaseType.SQLITE, options);
		
		Query query = databaseSchema.createQuery(ChatMessage.class, QueryOptions.build().where("uid=#{uid}").paramsClass(P.class));
		
		logger.info(""+query.columns.length);
	}
}
