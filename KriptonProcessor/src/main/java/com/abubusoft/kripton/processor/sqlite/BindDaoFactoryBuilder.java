package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;
import com.abubusoft.kripton.processor.BindDatabaseProcessor;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
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

	/**
	 * Return built interface name.
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public String buildDaoFactoryInterface(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		String schemaName =  schema.getName();
		schemaName=PREFIX+schemaName;		
		
		schemaName=schemaName.replace("Database", SUFFIX);
		
		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		builder = TypeSpec.interfaceBuilder(schemaName).addModifiers(Modifier.PUBLIC).addSuperinterface(BindDaoFactory.class);
						
		for (SQLDaoDefinition dao : schema.getCollection()) {
			ClassName daoImplName = className(BindDatabaseBuilder.PREFIX+ dao.getName());					
			
			// dao with external connections
			{
				MethodSpec.Builder methodBuilder=MethodSpec.methodBuilder("get"+dao.getName()).addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).addParameter(SQLiteDatabase.class, "database").returns(daoImplName);
				builder.addMethod(methodBuilder.build());
			}
		}		

		TypeSpec typeSpec = builder.build();
		BindDatabaseProcessor.info("WRITE "+typeSpec.name);		
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
		
		return schemaName;
	}

}
