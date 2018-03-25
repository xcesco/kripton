/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource.AbstractExecutable;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource.OnErrorListener;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContextInSessionImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.Version;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.element.GeneratedTypeElement;
import com.abubusoft.kripton.processor.exceptions.CircularRelationshipException;
import com.abubusoft.kripton.processor.sqlite.core.EntityUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.FieldSpec.Builder;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import android.database.sqlite.SQLiteDatabase;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.subjects.PublishSubject;

/**
 * Generates database class
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindDataSourceBuilder extends AbstractBuilder {

	public static final String PREFIX = "Bind";

	public static final String SUFFIX = "DataSource";

	public BindDataSourceBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
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
		visitor.buildDaoFactoryInterface(elementUtils, filer, schema);
		String daoFactoryName = BindDaoFactoryBuilder.generateDaoFactoryName(schema);

		BindDataSourceBuilder visitorDao = new BindDataSourceBuilder(elementUtils, filer, schema);
		visitorDao.buildDataSource(elementUtils, filer, schema, daoFactoryName);

		generateSchema(schema);
	}

	/**
	 * @param schema
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void generateSchema(SQLiteDatabaseSchema schema) throws FileNotFoundException, IOException {
		// when we run in JUNIT of Kripton, we don't have to generate schemas
		// if (BindDataSourceSubProcessor.JUNIT_TEST_MODE)
		// return;
		if (!schema.generateSchema)
			return;

		// and now, write schema.create.v and schema.drop.v
		String schemaCreation = defineFileName(schema);
		File schemaCreatePath = new File("schemas").getAbsoluteFile();
		File schemaCreateFile = new File("schemas/" + schemaCreation).getAbsoluteFile();
		schemaCreatePath.mkdirs();

		AnnotationProcessorUtilis.infoOnGeneratedFile(BindDataSource.class, schemaCreateFile);
		FileOutputStream fos = new FileOutputStream(schemaCreateFile);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		bw.write("------------------------------------------------------------------------------------\n");
		bw.write("--\n");
		bw.write("-- Filename: " + schemaCreation + "\n");
		bw.write("--\n");
		bw.write(String.format("-- Since: %s", (new Date()).toString()) + "\n");
		bw.write("--\n");
		if (!BindDataSourceSubProcessor.JUNIT_TEST_MODE) {
			bw.write(String.format("-- This file was generated by Kripton Annotation Processor v. %s\n", Version.getVersion()));
			bw.write(String.format("--\n"));
		}
		bw.write("------------------------------------------------------------------------------------\n");
		bw.newLine();
		for (String sql : schema.sqlForCreate) {
			bw.write(sql);
			bw.newLine();
		}
		bw.close();
	}

	static String defineFileName(SQLiteDatabaseSchema model) {
		int lastIndex = model.fileName.lastIndexOf(".");
		String schemaName = model.fileName;

		if (lastIndex > -1) {
			schemaName = model.fileName.substring(0, lastIndex);
		}

		schemaName = schemaName.toLowerCase() + "_schema_" + model.version + ".sql";

		return schemaName;
	}

	public void buildDataSource(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema, String daoFactoryName) throws Exception {
		ClassName daoFactoryClazz = className(daoFactoryName);
		Converter<String, String> convert = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);

		String dataSourceName = schema.getName();
		dataSourceName = PREFIX + dataSourceName;

		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? "" : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, dataSourceName);
		classBuilder = TypeSpec.classBuilder(dataSourceName).addModifiers(Modifier.PUBLIC).superclass(AbstractDataSource.class).addSuperinterface(daoFactoryClazz)
				.addSuperinterface(TypeUtility.typeName(schema.getElement().asType()));

		classBuilder.addJavadoc("<p>\n");
		classBuilder.addJavadoc("Represents implementation of datasource $L.\n", schema.getName());
		classBuilder.addJavadoc("This class expose database interface through Dao attribute.\n", schema.getName());
		classBuilder.addJavadoc("</p>\n\n");

		JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		classBuilder.addJavadoc("@see $T\n", className(schema.getName()));
		classBuilder.addJavadoc("@see $T\n", daoFactoryClazz);
		for (SQLDaoDefinition dao : schema.getCollection()) {
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);
			classBuilder.addJavadoc("@see $T\n", dao.getElement());
			classBuilder.addJavadoc("@see $T\n", daoImplName);

			String entity = BindDataSourceSubProcessor.generateEntityName(dao, dao.getEntity());

			classBuilder.addJavadoc("@see $T\n", TypeUtility.typeName(entity));
		}

		// define static fields

		// instance
		classBuilder.addField(FieldSpec.builder(className(dataSourceName), "instance", Modifier.STATIC).addJavadoc("<p>datasource singleton</p>\n").build());

		// instance
		// classBuilder.addField(FieldSpec.builder(Boolean.TYPE, "justCreated",
		// Modifier.PRIVATE).addJavadoc("<p>True if dataSource is just
		// created</p>\n").build());

		for (SQLDaoDefinition dao : schema.getCollection()) {
			// TypeName daoInterfaceName =
			// BindDaoBuilder.daoInterfaceTypeName(dao);
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);
			classBuilder.addField(
					FieldSpec.builder(daoImplName, convert.convert(dao.getName()), Modifier.PROTECTED).addJavadoc("<p>dao instance</p>\n").initializer("new $T(context)", daoImplName).build());

			// dao with connections
			{
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("get" + dao.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).returns(BindDaoBuilder.daoTypeName(dao));
				methodBuilder.addCode("return $L;\n", convert.convert(dao.getName()));
				classBuilder.addMethod(methodBuilder.build());
			}
		}

		if (schema.generateRx) {
			generateRx(className(dataSourceName), daoFactoryName);

			for (SQLDaoDefinition dao : schema.getCollection()) {
				// subject
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, dao.getEntitySimplyClassName() + "Subject"))
						.addModifiers(Modifier.PUBLIC);
				methodBuilder.addStatement("return $L.subject()", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, convert.convert(dao.getName())))
						.returns(ParameterizedTypeName.get(PublishSubject.class, SQLiteEvent.class));
				classBuilder.addMethod(methodBuilder.build());
			}
		}

		// interface
		generateMethodExecuteTransaction(daoFactoryName);
		generateMethodExecuteBatch(daoFactoryName);

		// generate instance
		generateInstance(schema, dataSourceName);

		// generate open
		generateOpen(dataSourceName);

		// generate openReadOnly
		generateOpenReadOnly(dataSourceName);

		// generate constructor
		generateConstructor(schema);

		// before use entities, order them with dependencies respect
		List<SQLEntity> orderedEntities = generateOrderedEntitiesList(schema);

		// onCreate
		boolean useForeignKey = generateOnCreate(schema, orderedEntities);

		// onUpgrade
		generateOnUpgrade(schema, orderedEntities);

		// onConfigure
		generateOnConfigure(useForeignKey);

		// generate
		generateDaoUids(classBuilder, schema, "");

		//
		// generate prepared statement cleaner
		{

			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("clearCompiledStatements").addModifiers(Modifier.PUBLIC).returns(Void.TYPE);
			for (SQLDaoDefinition dao : schema.getCollection()) {
				methodBuilder.addStatement("$T.clearCompiledStatements()", TypeUtility.className(dao.getElement().getQualifiedName().toString() + "Impl"));
			}

			classBuilder.addMethod(methodBuilder.build());
		}

		// generate single thread datasource
		generateDataSourceSingleThread(schema, dataSourceName);

		// build
		generateBuild(dataSourceName, schema);

		{
			Builder f = FieldSpec.builder(ArrayTypeName.of(SQLiteTable.class), "TABLES", Modifier.FINAL, Modifier.STATIC).addJavadoc("List of tables compose datasource\n");
			com.squareup.javapoet.CodeBlock.Builder c = CodeBlock.builder();
			String s = "";

			c.add("{");
			for (SQLEntity entity : schema.getEntities()) {
				String tableName = BindTableGenerator.getTableClassName(entity.getName());

				c.add(s + "new $T()", TypeUtility.className(tableName));
				s = ", ";
			}

			for (GeneratedTypeElement entity : schema.generatedEntities) {
				String tableName = BindTableGenerator.getTableClassName(entity.getQualifiedName());

				c.add(s + "new $T()", TypeUtility.className(tableName));
				s = ", ";
			}

			c.add("}");
			f.initializer(c.build());
			classBuilder.addField(f.build());

			classBuilder.addMethod(MethodSpec.methodBuilder("tables").addJavadoc("List of tables compose datasource:\n").addModifiers(Modifier.PUBLIC, Modifier.STATIC).addStatement("return TABLES")
					.returns(ArrayTypeName.of(SQLiteTable.class)).build());
		}

		TypeSpec typeSpec = classBuilder.build();
		JavaWriterHelper.writeJava2File(filer, packageName, typeSpec);
	}

	/**
	 * @param schema
	 */
	private void generateConstructor(SQLiteDatabaseSchema schema) {
		// constructor
		MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder().addParameter(DataSourceOptions.class, "options").addModifiers(Modifier.PROTECTED);
		methodBuilder.addStatement("super($S, $L, options)", schema.isInMemory() ? null : schema.fileName, schema.version);
		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate dao arrays
	 * 
	 * @param schema
	 * @param orderedEntities
	 * @return 
	 */
	public static void generateDaoUids(TypeSpec.Builder classBuilder, SQLiteDatabaseSchema schema, String prefix) {
		
		for (SQLDaoDefinition dao : schema.getCollection()) {			
			
			classBuilder.addField(FieldSpec.builder(Integer.TYPE, dao.daoUidName, Modifier.FINAL, Modifier.STATIC, Modifier.PUBLIC).initializer(""+dao.daoUidValue)
					.addJavadoc("Unique identifier for Dao $L\n", dao.getName())
					.build());		
		}		
	}

	private void generateDataSourceSingleThread(SQLiteDatabaseSchema schema, String dataSourceName) {
		// class DataSourceSingleThread
		String daoFactoryName = BindDaoFactoryBuilder.generateDaoFactoryName(schema);
		TypeSpec.Builder clazzBuilder = TypeSpec.classBuilder("DataSourceSingleThread").addSuperinterface(TypeUtility.typeName(daoFactoryName));

		// _context field
		clazzBuilder.addField(FieldSpec.builder(SQLContextInSessionImpl.class, "_context", Modifier.PRIVATE).build());

		// constructor
		MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder();
		constructorBuilder.addStatement("_context=new $T($L.this)", SQLContextInSessionImpl.class, dataSourceName);

		// all dao
		for (SQLDaoDefinition dao : schema.getCollection()) {
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);

			// dao with external connections
			{
				String daoFieldName = "_" + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, dao.getName());
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("get" + dao.getName()).addModifiers(Modifier.PUBLIC).addJavadoc("\nretrieve dao $L\n", dao.getName()).returns(daoImplName);
				methodBuilder.beginControlFlow("if ($L==null)",daoFieldName);
				methodBuilder.addStatement("$L=new $T(_context)",daoFieldName, daoImplName);
				methodBuilder.endControlFlow();
				methodBuilder.addStatement("return $L", daoFieldName);
				clazzBuilder.addMethod(methodBuilder.build());

				clazzBuilder.addField(FieldSpec.builder(daoImplName, daoFieldName, Modifier.PROTECTED).build());
//				constructorBuilder.addStatement("$L=new $T(_context)", daoFieldName, daoImplName);
//				constructorBuilder.addStatement("DAOS=new $T[$L]", Dao.class, schema.getEntitiesAsList().size());
//				for (int i=0;i<schema.getEntitiesAsList().size();i++) {
//					constructorBuilder.addStatement("DAOS[$L]=$L", i,daoFieldName);
//				}
			}
		}

		// constructor
		clazzBuilder.addMethod(constructorBuilder.build());
		
		// onSessionOpened
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onSessionOpened")					
					.addModifiers(Modifier.PROTECTED)
					.returns(Void.TYPE);
			methodBuilder.addStatement("_context.onSessionOpened()");
			
			clazzBuilder.addMethod(methodBuilder.build());
		}
		
		// onSessionClosed
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onSessionClosed")					
					.addModifiers(Modifier.PROTECTED)
					.returns(ParameterizedTypeName.get(Set.class, String.class));
			methodBuilder.addStatement("return _context.onSessionClosed()");
			
			clazzBuilder.addMethod(methodBuilder.build());
		}
				

		// build method bindToThread
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bindToThread").addModifiers(Modifier.PUBLIC).returns(TypeUtility.typeName("DataSourceSingleThread"));

			// methodBuilder.addStatement("_context.bindToThread()");
			methodBuilder.addStatement("return this");
			clazzBuilder.addMethod(methodBuilder.build());

			// build single thread daoFactory used in transaction
			classBuilder.addField(FieldSpec.builder(TypeUtility.typeName("DataSourceSingleThread"), "_daoFactorySingleThread", Modifier.PROTECTED)
					.addJavadoc("Used only in transactions (that can be executed one for time").initializer("new DataSourceSingleThread()").build());
		}

		classBuilder.addType(clazzBuilder.build());

	}

	private void generateBuild(String dataSourceName, SQLiteDatabaseSchema schema) {
		{ // build with parameter
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("build").addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.SYNCHRONIZED).addParameter(DataSourceOptions.class, "options")
					.returns(TypeUtility.mergeTypeName(PREFIX, schema.getElement()));

			methodBuilder.addJavadoc("Build instance.\n");
			methodBuilder.addJavadoc("@return dataSource instance.\n");

			methodBuilder.beginControlFlow("if (instance==null)");
			methodBuilder.addStatement("instance=new $L(options)", dataSourceName);
			methodBuilder.endControlFlow();

			generatePopulate(schema, methodBuilder);

			methodBuilder.addCode("return instance;\n");

			classBuilder.addMethod(methodBuilder.build());
		}

		{ // build without parameter
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("build").returns(TypeUtility.mergeTypeName(PREFIX, schema.getElement())).addModifiers(Modifier.PUBLIC, Modifier.STATIC,
					Modifier.SYNCHRONIZED);

			methodBuilder.addJavadoc("Build instance with default config.\n");
			methodBuilder.addStatement("return build(DataSourceOptions.builder().build())");

			classBuilder.addMethod(methodBuilder.build());
		}

	}

	/**
	 * @param schemaName
	 */
	private void generateInstance(SQLiteDatabaseSchema schema, String schemaName) {
		// instance
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("instance").addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.SYNCHRONIZED).returns(className(schemaName));

		methodBuilder.beginControlFlow("if (instance==null)");
		methodBuilder.addCode("$T options=$T.builder()", DataSourceOptions.class, DataSourceOptions.class);
		if (schema.getDefaultTasks() != null && schema.getDefaultTasks().size() > 0) {
			for (Pair<Integer, String> task : schema.getDefaultTasks()) {
				methodBuilder.addCode("\n\t.addUpdateTask($L, new $T())", task.value0, TypeUtility.className(task.value1));
			}
		}
		if (schema.populatorClazz != null) {
			methodBuilder.addCode("\n\t.populator(new $T())", TypeUtility.className(schema.populatorClazz));
		}
		if (schema.isInMemory()) {
			methodBuilder.addCode("\n\t.inMemory(true)");
		}
		methodBuilder.addCode("\n\t.build();\n", DataSourceOptions.class, DataSourceOptions.class);
		methodBuilder.addStatement("instance=new $L(options)", schemaName);

		generatePopulate(schema, methodBuilder);

		methodBuilder.endControlFlow();

		methodBuilder.addJavadoc("instance\n");
		methodBuilder.addCode("return instance;\n");

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * @param schema
	 * @param methodBuilder
	 */
	private void generatePopulate(SQLiteDatabaseSchema schema, MethodSpec.Builder methodBuilder) {
		if (schema.populatorClazz != null) {
			methodBuilder.addComment("force database DDL run");
			methodBuilder.beginControlFlow("if (options.populator!=null)");

			methodBuilder.addStatement("instance.openWritableDatabase()");
			methodBuilder.addStatement("instance.close()");

			methodBuilder.beginControlFlow("if (instance.justCreated)");

			methodBuilder.addComment("run populator");
			methodBuilder.addStatement("options.populator.execute()");

			methodBuilder.endControlFlow();
			methodBuilder.endControlFlow();
		}
	}

	/**
	 * @param schemaName
	 */
	private void generateOpen(String schemaName) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("open").addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(className(schemaName));

		methodBuilder.addJavadoc("Retrieve data source instance and open it.\n");
		methodBuilder.addJavadoc("@return opened dataSource instance.\n");

		methodBuilder.addStatement("$L instance=instance()", schemaName);

		methodBuilder.addStatement("instance.openWritableDatabase()");
		methodBuilder.addCode("return instance;\n");

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * @param schemaName
	 */
	private void generateOpenReadOnly(String schemaName) {
		// instance
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("openReadOnly").addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(className(schemaName));

		methodBuilder.addJavadoc("Retrieve data source instance and open it in read only mode.\n");
		methodBuilder.addJavadoc("@return opened dataSource instance.\n");

		methodBuilder.addStatement("$L instance=instance()", schemaName);

		methodBuilder.addStatement("instance.openReadOnlyDatabase()");
		methodBuilder.addCode("return instance;\n");

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * @param schema
	 * @param orderedEntities
	 */
	private boolean generateOnCreate(SQLiteDatabaseSchema schema, List<SQLEntity> orderedEntities) {
		boolean useForeignKey = false;
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onCreate").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
		methodBuilder.addParameter(SQLiteDatabase.class, "database");
		methodBuilder.addJavadoc("onCreate\n");
		methodBuilder.addCode("// generate tables\n");
		if (schema.isLogEnabled()) {
			// generate log section - BEGIN
			methodBuilder.addComment("log section BEGIN");
			methodBuilder.beginControlFlow("if (this.logEnabled)");

			methodBuilder.beginControlFlow("if (options.inMemory)");
			methodBuilder.addStatement("$T.info(\"Create database in memory\")", Logger.class);
			methodBuilder.nextControlFlow("else");
			methodBuilder.addStatement("$T.info(\"Create database '%s' version %s\",this.name, this.version)", Logger.class);
			methodBuilder.endControlFlow();

			// generate log section - END
			methodBuilder.endControlFlow();
			methodBuilder.addComment("log section END");
		}
		for (SQLEntity item : orderedEntities) {
			if (schema.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (this.logEnabled)");

				methodBuilder.addStatement("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL)", Logger.class, BindTableGenerator.tableClassName(null, item));

				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}
			methodBuilder.addStatement("database.execSQL($T.CREATE_TABLE_SQL)", BindTableGenerator.tableClassName(null, item));

			if (item.referedEntities.size() > 0) {
				useForeignKey = true;
			}
		}

		// use generated entities too
		// if we have generated entities, we use foreign key for sure
		if (schema.generatedEntities.size() > 0)
			useForeignKey = true;
		for (GeneratedTypeElement item : schema.generatedEntities) {
			if (schema.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (this.logEnabled)");

				methodBuilder.addStatement("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL)", Logger.class, TypeUtility.className(BindTableGenerator.getTableClassName(item.getQualifiedName())));

				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}
			methodBuilder.addStatement("database.execSQL($T.CREATE_TABLE_SQL)", TypeUtility.className(BindTableGenerator.getTableClassName(item.getQualifiedName())));

		}

		methodBuilder.beginControlFlow("if (options.databaseLifecycleHandler != null)");
		methodBuilder.addStatement("options.databaseLifecycleHandler.onCreate(database)");
		methodBuilder.endControlFlow();

		methodBuilder.addStatement("justCreated=true");

		classBuilder.addMethod(methodBuilder.build());

		return useForeignKey;
	}

	/**
	 * @param schema
	 * @param orderedEntities
	 */
	private void generateOnUpgrade(SQLiteDatabaseSchema schema, List<SQLEntity> orderedEntities) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onUpgrade").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
		methodBuilder.addParameter(SQLiteDatabase.class, "database");
		methodBuilder.addParameter(Integer.TYPE, "previousVersion");
		methodBuilder.addParameter(Integer.TYPE, "currentVersion");
		methodBuilder.addJavadoc("onUpgrade\n");

		Collections.reverse(orderedEntities);

		if (schema.isLogEnabled()) {
			// generate log section - BEGIN
			methodBuilder.addComment("log section BEGIN");
			methodBuilder.beginControlFlow("if (this.logEnabled)");

			methodBuilder.addStatement("$T.info(\"Update database '%s' from version %s to version %s\",this.name, previousVersion, currentVersion)", Logger.class);

			// generate log section - END
			methodBuilder.endControlFlow();
			methodBuilder.addComment("log section END");
		}

		methodBuilder.addComment("if we have a list of update task, try to execute them");
		methodBuilder.beginControlFlow("if (options.updateTasks != null)");
		methodBuilder.addStatement("$T<$T> tasks = buildTaskList(previousVersion, currentVersion)", List.class, SQLiteUpdateTask.class);
		methodBuilder.beginControlFlow("for ($T task : tasks)", SQLiteUpdateTask.class);

		// generate log section - BEGIN
		methodBuilder.addComment("log section BEGIN");
		methodBuilder.beginControlFlow("if (this.logEnabled)");

		methodBuilder.addStatement("$T.info(\"Begin update database from version %s to %s\", previousVersion, previousVersion+1)", Logger.class);

		// generate log section - END
		methodBuilder.endControlFlow();
		methodBuilder.addComment("log section END");

		methodBuilder.addStatement("task.execute(database)");

		// generate log section - BEGIN
		methodBuilder.addComment("log section BEGIN");
		methodBuilder.beginControlFlow("if (this.logEnabled)");

		methodBuilder.addStatement("$T.info(\"End update database from version %s to %s\", previousVersion, previousVersion+1)", Logger.class);

		// generate log section - END
		methodBuilder.endControlFlow();
		methodBuilder.addComment("log section END");

		methodBuilder.addStatement("previousVersion++");
		methodBuilder.endControlFlow();
		methodBuilder.nextControlFlow("else");

		methodBuilder.addComment("drop all tables");
		methodBuilder.addStatement("$T.dropTablesAndIndices(database)", SQLiteUpdateTaskHelper.class);

		// reorder entities
		Collections.reverse(orderedEntities);

		methodBuilder.addCode("\n");
		methodBuilder.addCode("// generate tables\n");

		for (SQLEntity item : orderedEntities) {
			if (schema.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (this.logEnabled)");

				methodBuilder.addCode("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL);\n", Logger.class, BindTableGenerator.tableClassName(null, item));

				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}
			methodBuilder.addCode("database.execSQL($T.CREATE_TABLE_SQL);\n", BindTableGenerator.tableClassName(null, item));
		}

		// use generated entities too
		for (GeneratedTypeElement item : schema.generatedEntities) {
			if (schema.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (this.logEnabled)");
				methodBuilder.addStatement("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL)", Logger.class, TypeUtility.className(BindTableGenerator.getTableClassName(item.getQualifiedName())));
				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}
			methodBuilder.addStatement("database.execSQL($T.CREATE_TABLE_SQL)", TypeUtility.className(BindTableGenerator.getTableClassName(item.getQualifiedName())));
		}

		methodBuilder.endControlFlow();

		methodBuilder.beginControlFlow("if (options.databaseLifecycleHandler != null)");
		methodBuilder.addStatement("options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true)");
		methodBuilder.endControlFlow();

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * @param useForeignKey
	 */
	private void generateOnConfigure(boolean useForeignKey) {
		// onConfigure

		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onConfigure").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
		methodBuilder.addParameter(SQLiteDatabase.class, "database");
		methodBuilder.addJavadoc("onConfigure\n");
		methodBuilder.addCode("// configure database\n");

		if (useForeignKey) {
			methodBuilder.addStatement("database.setForeignKeyConstraintsEnabled(true)");
		}

		methodBuilder.beginControlFlow("if (options.databaseLifecycleHandler != null)");
		methodBuilder.addStatement("options.databaseLifecycleHandler.onConfigure(database)");
		methodBuilder.endControlFlow();

		classBuilder.addMethod(methodBuilder.build());
	}

	private List<SQLEntity> generateOrderedEntitiesList(SQLiteDatabaseSchema schema) {
		List<SQLEntity> entities = schema.getEntitiesAsList();
		Collections.sort(entities, new Comparator<SQLEntity>() {

			@Override
			public int compare(SQLEntity lhs, SQLEntity rhs) {
				return lhs.getTableName().compareTo(rhs.getTableName());
			}
		});

		List<SQLEntity> list = schema.getEntitiesAsList();

		EntityUtility<SQLEntity> sorder = new EntityUtility<SQLEntity>(list) {

			@Override
			public Collection<SQLEntity> getDependencies(SQLEntity item) {
				return item.referedEntities;
			}

			@Override
			public void generateError(SQLEntity item) {
				throw new CircularRelationshipException(item);
			}
		};

		return sorder.order();
	}

	public void generatExecuteTransactionRx(ClassName dataSourceName, String daoFactory, RxType rxType) {
		String parameterName = "transaction";
		ParameterizedTypeName returnTypeName = ParameterizedTypeName.get(ClassName.get(rxType.clazz), TypeVariableName.get("T"));
		ParameterizedTypeName observableTypeName = ParameterizedTypeName.get(TypeUtility.className(rxType.clazz.getPackage().getName(), rxType.clazz.getSimpleName() + "OnSubscribe"),
				TypeVariableName.get("T"));
		ParameterizedTypeName emitterTypeName = ParameterizedTypeName.get(TypeUtility.className(rxType.clazz.getPackage().getName(), rxType.clazz.getSimpleName() + "Emitter"),
				TypeVariableName.get("T"));

		TypeSpec innerEmitter = TypeSpec.anonymousClassBuilder("").addSuperinterface(observableTypeName).addMethod(MethodSpec.methodBuilder("subscribe").addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC).addParameter(emitterTypeName, "emitter").returns(Void.TYPE)
				.addStatement("boolean needToOpened=!$L.this.isOpenInWriteMode()", dataSourceName.simpleName()).addCode("@SuppressWarnings(\"resource\")\n")
				.addStatement("$T connection=needToOpened ? openWritableDatabase() : database()", SQLiteDatabase.class).beginControlFlow("try").addStatement("connection.beginTransaction()")
				.beginControlFlow("if (transaction != null && $T.$L==transaction.onExecute(_daoFactorySingleThread.bindToThread(), emitter))", TransactionResult.class, TransactionResult.COMMIT)
				.addStatement("connection.setTransactionSuccessful()").endControlFlow().addStatement(rxType.onComplete ? "emitter.onComplete()" : "// no onComplete")
				.nextControlFlow("catch($T e)", Throwable.class).addStatement("$T.error(e.getMessage())", Logger.class).addStatement("e.printStackTrace()").addStatement("emitter.onError(e)")
				.nextControlFlow("finally").beginControlFlow("try").addStatement("connection.endTransaction()").nextControlFlow("catch($T e)", Throwable.class).endControlFlow()
				.addCode("if (needToOpened) { close(); }\n").endControlFlow().addStatement("return").build()).build();

		{
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC).addTypeVariable(TypeVariableName.get("T"))
					.addParameter(ParameterizedTypeName.get(TypeUtility.className(dataSourceName.toString(), rxType.clazz.getSimpleName() + "Transaction"), TypeVariableName.get("T")), parameterName,
							Modifier.FINAL)
					.returns(returnTypeName);

			executeMethod.addStatement("$T emitter=$L", observableTypeName, innerEmitter);

			if (rxType == RxType.FLOWABLE) {
				executeMethod.addStatement("$T result=$T.create(emitter, $T.BUFFER)", returnTypeName, rxType.clazz, BackpressureStrategy.class);
			} else {
				executeMethod.addStatement("$T result=$T.create(emitter)", returnTypeName, rxType.clazz);
			}
			executeMethod.addStatement("if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn)");
			executeMethod.addStatement("if (globalObserveOn!=null) result.observeOn(globalObserveOn)");

			executeMethod.addStatement("return result");

			classBuilder.addMethod(executeMethod.build());
		}

	}

	public void generatExecuteBatchRx(ClassName dataSourceName, String daoFactory, RxType rxType) {
		String parameterName = "batch";
		// @formatter:off
		ParameterizedTypeName returnTypeName = ParameterizedTypeName.get(ClassName.get(rxType.clazz),
				TypeVariableName.get("T"));
		ParameterizedTypeName observableTypeName = ParameterizedTypeName.get(TypeUtility
				.className(rxType.clazz.getPackage().getName(), rxType.clazz.getSimpleName() + "OnSubscribe"),
				TypeVariableName.get("T"));
		ParameterizedTypeName emitterTypeName = ParameterizedTypeName.get(
				TypeUtility.className(rxType.clazz.getPackage().getName(), rxType.clazz.getSimpleName() + "Emitter"),
				TypeVariableName.get("T"));

		TypeSpec innerEmitter = TypeSpec.anonymousClassBuilder("").addSuperinterface(observableTypeName)
				.addMethod(MethodSpec.methodBuilder("subscribe").addAnnotation(Override.class)
						.addModifiers(Modifier.PUBLIC).addParameter(emitterTypeName, "emitter").returns(Void.TYPE)

						.addStatement("boolean needToOpened=writeMode?!$L.this.isOpenInWriteMode(): !$L.this.isOpen()",
								dataSourceName.simpleName(), dataSourceName.simpleName())
						.addCode(
								"if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}\n",
								SQLiteDatabase.class)

						// .addStatement("if (writeMode) open(); else
						// openReadOnly()")
						.beginControlFlow("try")
						.addCode("if ($L != null) { $L.onExecute(new DataSourceSingleThread(), emitter); }\n",
								parameterName, parameterName)
						.addStatement(rxType.onComplete ? "emitter.onComplete()" : "// no onComplete")
						.nextControlFlow("catch($T e)", Throwable.class)
						.addStatement("$T.error(e.getMessage())", Logger.class).addStatement("e.printStackTrace()")
						.addStatement("emitter.onError(e)").nextControlFlow("finally")
						// .addStatement("close()")
						.addCode("if (needToOpened) { close(); }\n").endControlFlow().addStatement("return").build())
				.build();

		{
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("executeBatch").addModifiers(Modifier.PUBLIC)
					.addTypeVariable(TypeVariableName.get("T"))
					.addParameter(
							ParameterizedTypeName.get(TypeUtility.className(dataSourceName.toString(),
									rxType.clazz.getSimpleName() + "Batch"), TypeVariableName.get("T")),
							parameterName, Modifier.FINAL)
					.addParameter(TypeName.BOOLEAN, "writeMode", Modifier.FINAL).returns(returnTypeName);

			executeMethod.addStatement("$T emitter=$L", observableTypeName, innerEmitter);

			if (rxType == RxType.FLOWABLE) {
				executeMethod.addStatement("$T result=$T.create(emitter, $T.BUFFER)", returnTypeName, rxType.clazz,
						BackpressureStrategy.class);
			} else {
				executeMethod.addStatement("$T result=$T.create(emitter)", returnTypeName, rxType.clazz);
			}
			executeMethod.addStatement("if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn)");
			executeMethod.addStatement("if (globalObserveOn!=null) result.observeOn(globalObserveOn)");

			executeMethod.addStatement("return result");

			classBuilder.addMethod(executeMethod.build());
		}

		{
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("executeBatch").addModifiers(Modifier.PUBLIC)
					.addTypeVariable(TypeVariableName.get("T"))
					.addParameter(
							ParameterizedTypeName.get(TypeUtility.className(dataSourceName.toString(),
									rxType.clazz.getSimpleName() + "Batch"), TypeVariableName.get("T")),
							parameterName, Modifier.FINAL)
					.returns(returnTypeName);

			executeMethod.addStatement("return executeBatch($L, false)", parameterName);

			classBuilder.addMethod(executeMethod.build());
		}
		// @formatter:on
	}

	/**
	 * <p>
	 * Generate RX observable support
	 * </p>
	 * 
	 * @param dataSourceName
	 * 
	 * @param dataSource
	 */
	public void generateRx(ClassName dataSourceName, String daoFactory) {
		classBuilder.addField(FieldSpec.builder(Scheduler.class, "globalSubscribeOn", Modifier.PROTECTED).build());
		classBuilder.addMethod(MethodSpec.methodBuilder("globalSubscribeOn").returns(dataSourceName).addParameter(Scheduler.class, "scheduler").addModifiers(Modifier.PUBLIC)
				.addStatement("this.globalSubscribeOn=scheduler").addStatement("return this").build());

		classBuilder.addField(FieldSpec.builder(Scheduler.class, "globalObserveOn", Modifier.PROTECTED).build());
		classBuilder.addMethod(MethodSpec.methodBuilder("globalObserveOn").addParameter(Scheduler.class, "scheduler").returns(dataSourceName).addModifiers(Modifier.PUBLIC)
				.addStatement("this.globalObserveOn=scheduler").addStatement("return this").build());

		generateRxInterface(daoFactory, RxInterfaceType.BATCH, ObservableEmitter.class);
		generateRxInterface(daoFactory, RxInterfaceType.TRANSACTION, ObservableEmitter.class);

		generateRxInterface(daoFactory, RxInterfaceType.BATCH, SingleEmitter.class);
		generateRxInterface(daoFactory, RxInterfaceType.TRANSACTION, SingleEmitter.class);

		generateRxInterface(daoFactory, RxInterfaceType.BATCH, FlowableEmitter.class);
		generateRxInterface(daoFactory, RxInterfaceType.TRANSACTION, FlowableEmitter.class);

		generateRxInterface(daoFactory, RxInterfaceType.BATCH, MaybeEmitter.class);
		generateRxInterface(daoFactory, RxInterfaceType.TRANSACTION, MaybeEmitter.class);

		generatExecuteTransactionRx(dataSourceName, daoFactory, RxType.OBSERVABLE);
		generatExecuteTransactionRx(dataSourceName, daoFactory, RxType.SINGLE);
		generatExecuteTransactionRx(dataSourceName, daoFactory, RxType.FLOWABLE);
		generatExecuteTransactionRx(dataSourceName, daoFactory, RxType.MAYBE);

		generatExecuteBatchRx(dataSourceName, daoFactory, RxType.OBSERVABLE);
		generatExecuteBatchRx(dataSourceName, daoFactory, RxType.SINGLE);
		generatExecuteBatchRx(dataSourceName, daoFactory, RxType.FLOWABLE);
		generatExecuteBatchRx(dataSourceName, daoFactory, RxType.MAYBE);

	}

	private enum RxInterfaceType {
		BATCH, TRANSACTION
	}

	private enum RxType {
		OBSERVABLE(Observable.class, true), SINGLE(Single.class, false), MAYBE(Maybe.class, false), FLOWABLE(Flowable.class, true);

		private RxType(Class<?> clazz, boolean onComplete) {
			this.clazz = clazz;
			this.onComplete = onComplete;
		}

		public Class<?> clazz;
		public boolean onComplete;

	}

	private void generateRxInterface(String daoFactory, RxInterfaceType interfaceType, Class<?> clazz) {
		// create interfaces
		{
			ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(clazz), TypeVariableName.get("T"));
			String preExecutorName = clazz.getSimpleName().replace("Emitter", "");
			String postExecutorName = com.abubusoft.kripton.common.CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, interfaceType.toString());
			// @formatter:off
			if (interfaceType == RxInterfaceType.BATCH) {
				classBuilder
						.addType(TypeSpec.interfaceBuilder(preExecutorName + postExecutorName)
								.addModifiers(Modifier.PUBLIC).addTypeVariable(TypeVariableName.get("T"))
								.addMethod(MethodSpec.methodBuilder("onExecute")
										.addParameter(className(daoFactory), "daoFactory")
										.addParameter(parameterizedTypeName, "emitter")
										.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).returns(Void.TYPE).build())
								// .addModifiers(Modifier.PUBLIC,
								// Modifier.ABSTRACT).returns(TransactionResult.class).build())
								.build());
			} else {
				classBuilder
						.addType(TypeSpec.interfaceBuilder(preExecutorName + postExecutorName)
								.addModifiers(Modifier.PUBLIC).addTypeVariable(TypeVariableName.get("T"))
								.addMethod(MethodSpec.methodBuilder("onExecute")
										.addParameter(className(daoFactory), "daoFactory")
										.addParameter(parameterizedTypeName, "emitter")
										// .addModifiers(Modifier.PUBLIC,
										// Modifier.ABSTRACT).returns(Void.TYPE).build())
										.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
										.returns(TransactionResult.class).build())
								.build());
			}

			// @formatter:on
		}
	}

	/**
	 * <p>
	 * Generate transaction an execute method
	 * </p>
	 * 
	 * @param dataSource
	 */
	public void generateMethodExecuteTransaction(String daoFactory) {
		// create interface
		String transationExecutorName = "Transaction";
		// @formatter:off
		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(className(AbstractExecutable.class),
				className(daoFactory));
		classBuilder.addType(TypeSpec.interfaceBuilder(transationExecutorName).addModifiers(Modifier.PUBLIC)
				.addSuperinterface(parameterizedTypeName).addJavadoc("Rapresents transational operation.\n")
				.addMethod(MethodSpec.methodBuilder("onExecute").addParameter(className(daoFactory), "daoFactory")
						.addJavadoc(
								"Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results\nor {@link TransactionResult#ROLLBACK} to rollback.")
						.addJavadoc("\nIf exception is thrown, a rollback will be done.")
						.addJavadoc("\n\n@param daoFactory\n@return\n@throws Throwable\n")
						.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).returns(TransactionResult.class).build())
				.build());
		// @formatter:on

		{
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC).addParameter(className(transationExecutorName), "transaction");
			// generate javadoc
			executeMethod.addJavadoc("<p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The"
					+ "drawback is only one transaction at time can be executed. The database will be open in write mode. This method uses default error listener to intercept errors.</p>\n");
			executeMethod.addJavadoc("\n");
			executeMethod.addJavadoc("@param transaction\n\ttransaction to execute\n");

			executeMethod.addStatement("execute(transaction, onErrorListener)");

			classBuilder.addMethod(executeMethod.build());
		}

		{
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC).addParameter(className(transationExecutorName), "transaction")
					.addParameter(className(OnErrorListener.class), "onErrorListener");

			executeMethod.addStatement("boolean needToOpened=!this.isOpenInWriteMode()");
			executeMethod.addCode("@SuppressWarnings(\"resource\")\n");
			executeMethod.addStatement("$T connection=needToOpened ? openWritableDatabase() : database()", SQLiteDatabase.class);

			executeMethod.beginControlFlow("try");
			executeMethod.addCode("connection.beginTransaction();\n");

			executeMethod.beginControlFlow("if (transaction!=null && $T.$L == transaction.onExecute(_daoFactorySingleThread.bindToThread()))", TransactionResult.class, TransactionResult.COMMIT);
			executeMethod.addCode("connection.setTransactionSuccessful();\n");
			executeMethod.endControlFlow();

			executeMethod.nextControlFlow("catch($T e)", Throwable.class);

			executeMethod.addStatement("$T.error(e.getMessage())", Logger.class);
			executeMethod.addStatement("e.printStackTrace()");
			executeMethod.addStatement("if (onErrorListener!=null) onErrorListener.onError(e)");

			executeMethod.nextControlFlow("finally");
			executeMethod.beginControlFlow("try");
			executeMethod.addStatement("connection.endTransaction()");
			executeMethod.nextControlFlow("catch ($T e)", Throwable.class);
			executeMethod.addStatement("$T.warn(\"error closing transaction %s\", e.getMessage())", Logger.class);
			executeMethod.endControlFlow();
			executeMethod.addCode("if (needToOpened) { close(); }\n");
			executeMethod.endControlFlow();

			// generate javadoc
			executeMethod.addJavadoc("<p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The"
					+ "drawback is only one transaction at time can be executed. The database will be open in write mode.</p>\n");
			executeMethod.addJavadoc("\n");
			executeMethod.addJavadoc("@param transaction\n\ttransaction to execute\n");
			executeMethod.addJavadoc("@param onErrorListener\n\terror listener\n");

			classBuilder.addMethod(executeMethod.build());
		}

	}

	/**
	 * <p>
	 * Generate transaction an execute method
	 * </p>
	 * 
	 * @param dataSource
	 */
	public void generateMethodExecuteBatch(String daoFactory) {
		// create interface
		String transationExecutorName = "Batch";
		// @formatter:off
		// ParameterizedTypeName parameterizedTypeName =
		// ParameterizedTypeName.get(className(AbstractExecutable.class),
		// className(daoFactory));
		classBuilder.addType(TypeSpec.interfaceBuilder(transationExecutorName).addModifiers(Modifier.PUBLIC)
				.addTypeVariable(TypeVariableName.get("T"))
				// .addSuperinterface(parameterizedTypeName)
				.addJavadoc("Rapresents batch operation.\n")
				.addMethod(MethodSpec.methodBuilder("onExecute").addJavadoc("Execute batch operations.")
						.addJavadoc("\n\n@param daoFactory\n@throws Throwable\n")
						.addParameter(className(daoFactory), "daoFactory")
						.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).returns(TypeVariableName.get("T")).build())
				.build());
		// @formatter:on

		// @formatter:on
		{
			// execute read/write mode
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("executeBatch")
					// generate javadoc
					.addTypeVariable(TypeVariableName.get("T"))
					.addJavadoc("<p>Executes a batch opening a read only connection. This method <strong>is thread safe</strong> to avoid concurrent problems.</p>\n\n")
					.addJavadoc("@param commands\n\tbatch to execute\n").addModifiers(Modifier.PUBLIC)
					.addParameter(ParameterizedTypeName.get(className(transationExecutorName), TypeVariableName.get("T")), "commands").returns(TypeVariableName.get("T"));

			executeMethod.addStatement("return executeBatch(commands, false)");
			classBuilder.addMethod(executeMethod.build());
		}

		{
			// execute read/write mode
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("executeBatch")
					// generate javadoc
					.addJavadoc("<p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. The"
							+ "drawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>\n")
					.addTypeVariable(TypeVariableName.get("T")).addJavadoc("\n").addJavadoc("@param commands\n\tbatch to execute\n")
					.addJavadoc("@param writeMode\n\ttrue to open connection in write mode, false to open connection in read only mode\n").addModifiers(Modifier.PUBLIC)
					.addParameter(ParameterizedTypeName.get(className(transationExecutorName), TypeVariableName.get("T")), "commands").addParameter(Boolean.TYPE, "writeMode")
					.returns(TypeVariableName.get("T"));

			executeMethod.addStatement("boolean needToOpened=writeMode?!this.isOpenInWriteMode(): !this.isOpen()");
			executeMethod.addCode("if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}\n", SQLiteDatabase.class);

			executeMethod.beginControlFlow("try");

			executeMethod.beginControlFlow("if (commands!=null)");
			executeMethod.addStatement("return commands.onExecute(new DataSourceSingleThread())");
			executeMethod.endControlFlow();

			executeMethod.nextControlFlow("catch($T e)", Throwable.class);

			executeMethod.addStatement("$T.error(e.getMessage())", Logger.class);
			executeMethod.addStatement("e.printStackTrace()");
			executeMethod.addStatement("throw(e)");

			executeMethod.nextControlFlow("finally");
			executeMethod.addCode("if (needToOpened) { close(); }\n");
			executeMethod.endControlFlow();
			executeMethod.addStatement("return null");

			classBuilder.addMethod(executeMethod.build());
		}
	}

}
