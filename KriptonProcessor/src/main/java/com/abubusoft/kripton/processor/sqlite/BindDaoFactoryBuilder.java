package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.sqlite.BindDaoFactory;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Generates database class
 * 
 * @author xcesco
 *
 */
public class BindDaoFactoryBuilder extends AbstractBuilder  {

	public static final String PREFIX = "Bind";
	
	public static final String SUFFIX = "DaoFactory";

	public BindDaoFactoryBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}
	
	public static String generateDaoFactoryName(SQLiteDatabaseSchema schema)
	{
		String schemaName = schema.getName();
		schemaName=PREFIX+schemaName;		
		
		schemaName=schemaName.replace(BindDataSourceBuilder.SUFFIX, SUFFIX);
		
		return schemaName;
	}

	/**
	 * Build dao factory interface
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * 
	 * @return
	 * 		schema name
	 * 
	 * @throws Exception
	 */
	public String buildDaoFactoryInterface(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		String schemaName =  generateDaoFactoryName(schema);
		
		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, schemaName);
		builder=buildDaoFactoryInterface(null, elementUtils, filer, schema);
		TypeSpec typeSpec = builder.build();
		//BindDatabaseProcessor.info("WRITE "+typeSpec.name);		
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
		
		return schemaName;
	}
	
	/**
	 * Build dao factory interface
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * 
	 * @return
	 * 		schema name
	 * 
	 * @throws Exception
	 */
	public TypeSpec.Builder buildDaoFactoryInterface(TypeSpec.Builder parentBuilder, Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		String schemaName =  schema.getName();
		schemaName=PREFIX+schemaName;		
		
		schemaName=schemaName.replace(BindDataSourceBuilder.SUFFIX, SUFFIX);
		
		builder = TypeSpec.interfaceBuilder(schemaName).addModifiers(Modifier.PUBLIC).addSuperinterface(BindDaoFactory.class);
		
		builder.addJavadoc("<p>\n");
		builder.addJavadoc("Represents dao factory interface for $L.\n",schema.getName());
		builder.addJavadoc("This class expose database interface through Dao attribute.\n",schema.getName());
		builder.addJavadoc("</p>\n\n");
		
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc("@see $T\n", TypeUtility.typeName(schema.getName()));
		for (SQLDaoDefinition dao : schema.getCollection()) {
		ClassName daoImplName = className(BindDataSourceBuilder.PREFIX+ dao.getName());
			builder.addJavadoc("@see $T\n", dao.getElement());
			builder.addJavadoc("@see $T\n", daoImplName);
			builder.addJavadoc("@see $T\n", dao.getEntity().getElement());
		}
						
		for (SQLDaoDefinition dao : schema.getCollection()) {
			ClassName daoImplName = className(BindDataSourceBuilder.PREFIX+dao.getName());					
			
			// dao with external connections
			{
				MethodSpec.Builder methodBuilder=MethodSpec.methodBuilder("get"+dao.getName())
						.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
						.addJavadoc("\nretrieve dao $L\n", dao.getName())
						.returns(daoImplName);
				builder.addMethod(methodBuilder.build());
			}
		}		

		if (parentBuilder!=null) {
			parentBuilder.addType(builder.build());
		}
		
		return builder;
	}

}
