package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractBindDatabaseHelper;
import com.abubusoft.kripton.processor.BindDatabaseProcessor;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * Generates database class
 * 
 * @author xcesco
 *
 */
public class BindDatabaseBuilder extends AbstractBuilder  {

	public static final String PREFIX = "Bind";
		
	public BindDatabaseBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Generate database
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * @throws Exception
	 */
	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		BindDaoFactoryBuilder visitor = new BindDaoFactoryBuilder(elementUtils, filer, schema);		
		String daoFactoryName=visitor.buildDaoFactoryInterface(elementUtils, filer, schema);
		
		BindDatabaseBuilder visitorDao = new BindDatabaseBuilder(elementUtils, filer, schema);		
		visitorDao.buildDatabase(elementUtils, filer, schema, daoFactoryName);
	}

	public void buildDatabase(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema, String daoFactoryName) throws Exception {
		ClassName daoFactory=className(daoFactoryName);
		Converter<String, String> convert = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		
		String schemaName =  schema.getName();
		schemaName=PREFIX+schemaName;
		
		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		builder = TypeSpec.classBuilder(schemaName).addModifiers(Modifier.PUBLIC).superclass(AbstractBindDatabaseHelper.class).addSuperinterface(daoFactory);
				
		// define static fields
		builder.addField(className(schemaName), "instance", Modifier.PRIVATE, Modifier.STATIC);		
		builder.addField(FieldSpec.builder(String.class, "name", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).initializer("$S", schema.fileName).build());		
		builder.addField(FieldSpec.builder(Integer.TYPE, "version", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).initializer("$L", schema.version).build());		
		
		for (SQLDaoDefinition dao : schema.getCollection()) {
			ClassName daoImplName = className(BindDatabaseBuilder.PREFIX+ dao.getName());
			builder.addField(FieldSpec.builder(daoImplName,convert.convert(dao.getName()), Modifier.PROTECTED).initializer("new $T()", daoImplName) .build());
			
			// dao with connections
			{
				MethodSpec.Builder methodBuilder=MethodSpec.methodBuilder("get"+dao.getName()).addModifiers(Modifier.PUBLIC).returns(className(BindDatabaseBuilder.PREFIX+dao.getName()));
				methodBuilder.addCode("$L.setDatabase(getWritableDatabase());\n", convert.convert(dao.getName()));
				methodBuilder.addCode("return $L;\n", convert.convert(dao.getName()));
				builder.addMethod(methodBuilder.build());
			}
			
			// dao with external connections
			{
				MethodSpec.Builder methodBuilder=MethodSpec.methodBuilder("get"+dao.getName()).addParameter(SQLiteDatabase.class, "database").addModifiers(Modifier.PUBLIC).returns(className(BindDatabaseBuilder.PREFIX+dao.getName())).addAnnotation(Override.class);
				methodBuilder.addCode("$L.setDatabase(database);\n", convert.convert(dao.getName()));
				methodBuilder.addCode("return $L;\n", convert.convert(dao.getName()));
				builder.addMethod(methodBuilder.build());
			}
		}
		
		// interface 
		//public interface DummyExecutor extends DatabaseExecutor
		generateMethodExecute(daoFactoryName);
		
		{
			// instance
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("instance").addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(className(schemaName));
	
			methodBuilder.addJavadoc("\n$L\n","instance");
			methodBuilder.beginControlFlow("if (instance==null)");
			methodBuilder.addCode("instance=new $L($T.context);\n", className(schemaName), KriptonLibrary.class);
			methodBuilder.endControlFlow();
			methodBuilder.addCode("return instance;\n");

			builder.addMethod(methodBuilder.build());
		}
		
		{
			// constructor
			MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PROTECTED).addParameter(Context.class, "context");
			methodBuilder.addCode("super(context, name, null, version);\n");			
			builder.addMethod(methodBuilder.build());
		}
		
		{
			// onCreate
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onCreate").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
			methodBuilder.addParameter(SQLiteDatabase.class, "database");
			methodBuilder.addJavadoc("\n$L\n","onCreate\n");
			
			for (SQLEntity item: schema.getEntities())
			{
				methodBuilder.addCode("database.execSQL($LTable.CREATE_TABLE_SQL);\n", item.getSimpleName());
				
			}

			builder.addMethod(methodBuilder.build());
		}

		{
			// onUpgrade
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onUpgrade").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
			methodBuilder.addParameter(SQLiteDatabase.class, "database");
			methodBuilder.addParameter(Integer.TYPE, "oldVersion");
			methodBuilder.addParameter(Integer.TYPE, "newVersion");
			methodBuilder.addJavadoc("\n$L\n","onUpgrade");

			methodBuilder.addCode("// drop tables\n");
			for (SQLEntity item: schema.getEntities())
			{
				methodBuilder.addCode("database.execSQL($LTable.DROP_TABLE_SQL);\n", item.getSimpleName());								
			}
			
			methodBuilder.addCode("\n");
			methodBuilder.addCode("// generate tables\n");
			
			for (SQLEntity item: schema.getEntities())
			{
				methodBuilder.addCode("database.execSQL($LTable.CREATE_TABLE_SQL);\n", item.getSimpleName());								
			}
			
			
			builder.addMethod(methodBuilder.build());			
		}

		TypeSpec typeSpec = builder.build();
		
		BindDatabaseProcessor.info("WRITE "+typeSpec.name);		
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

	/**
	 * <p>Generate execute method</p>
	 * 
	 * @param daoFactoryName
	 */
	public void generateMethodExecute(String daoFactoryName) {
		String transationExecutorName="Transaction";
		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(className("AbstractTransaction"), className(daoFactoryName));			
		builder.addType(TypeSpec.interfaceBuilder(transationExecutorName).addModifiers(Modifier.PUBLIC).addSuperinterface(parameterizedTypeName).build());
			
		MethodSpec.Builder executeMethod=MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC).addParameter(className(transationExecutorName),"transaction");
		executeMethod.addCode("$T database=getWritableDatabase();\n",SQLiteDatabase.class);
		executeMethod.beginControlFlow("try");
		executeMethod.addCode("database.beginTransaction();\n");
		
		executeMethod.beginControlFlow("if (transaction!=null && transaction.onExecute(this, database))");
		executeMethod.addCode("database.setTransactionSuccessful();\n");
		executeMethod.endControlFlow();
				
		executeMethod.nextControlFlow("finally");
		executeMethod.addCode("database.endTransaction();\n");
		executeMethod.endControlFlow();
		
		// generate javadoc
		executeMethod.addJavadoc("<p>Allow to execute a transaction. The database will be open in write mode.</p>\n");
		executeMethod.addJavadoc("\n");
		executeMethod.addJavadoc("@param transaction\n");
		executeMethod.addJavadoc("\ttransaction to execute\n");
		
		builder.addMethod(executeMethod.build());
	}

}
