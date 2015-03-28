package issue.kripton_22;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.Options;
import com.abubusoft.kripton.binder.database.DatabaseSchema;
import com.abubusoft.kripton.binder.database.DatabaseSchemaOptions;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

import issue.BaseTest;

public class Test01 extends BaseTest {

	@Test
	public void test01() throws MappingException, WriterException
	{	
		Bean01 bean=new Bean01();
		
		BinderWriter writer = BinderFactory.getXMLWriter(Options.build().indent(true));
		
		MappingSchema mappingSchema=MappingSchema.fromClass(Bean01.class);
		
		logger.info("\n"+writer.write(mappingSchema));
		
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.tablePrefix("TD_");
		options.add(Bean01.class);
		
		DatabaseSchema databaseSchema=DatabaseSchema.getFrom(options);
		
		for (Entry<String, MappingSchema> item :databaseSchema.tables.entrySet())
		{
			logger.info("table "+item.getKey()+" value: "+item.getValue().tableInfo.name);
			
			for (Entry<String, ElementSchema> element: item.getValue().getField2SchemaMapping().entrySet())
			{
				logger.info("\tcolumn "+element.getKey()+" value: "+element.getValue().getColumnInfo().name+" "+element.getValue().getColumnInfo().type);
			}
			
		}
		//logger.info(value);
		
	}
}
