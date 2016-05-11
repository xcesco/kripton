package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.sqlite.DaoFactory;
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
public class DaoFactoryGenerator extends AbstractCodeGenerator  {

	public static final String SUFFIX = "DaoFactory";
	
	public static final String SUFFIX_TO_REMOVE = "DatabaseSchema";

	public DaoFactoryGenerator(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Return built interface name
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * @return
	 * 
	 * @throws Exception
	 */
	public String buildDaoFactoryInterface(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		String schemaName =  schema.getName();
		if (schemaName.endsWith(SUFFIX_TO_REMOVE))
		{
			schemaName=schemaName.substring(0, schemaName.lastIndexOf(SUFFIX_TO_REMOVE));
		}
		
		if (!schemaName.endsWith(SUFFIX))
		{
			schemaName+=SUFFIX;
		}
		
		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		builder = TypeSpec.interfaceBuilder(schemaName).addModifiers(Modifier.PUBLIC).addSuperinterface(DaoFactory.class);
						
		for (SQLDaoDefinition dao : schema.getCollection()) {
			ClassName daoImplName = className(dao.getName()+"Impl");					
			
			// dao with external connections
			{
				MethodSpec.Builder methodBuilder=MethodSpec.methodBuilder("get"+dao.getName()).addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).addParameter(SQLiteDatabase.class, "database").returns(daoImplName);
				builder.addMethod(methodBuilder.build());
			}
		}
		

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
		
		return schemaName;
	}

}
