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
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource.AbstractExecutable;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource.OnErrorListener;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.android.sqlite.SQLContextInSessionImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.KriptonDynamicClassManager;
import com.abubusoft.kripton.processor.KriptonOptions;
import com.abubusoft.kripton.processor.Version;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.element.GeneratedTypeElement;
import com.abubusoft.kripton.processor.exceptions.CircularRelationshipException;
import com.abubusoft.kripton.processor.sqlite.core.EntitySorter;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
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
 * Generates database class.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class BindDataSourceBuilder extends AbstractBuilder {

	/** The Constant DATA_SOURCE_SINGLE_THREAD_NAME. */
	private static final String DATA_SOURCE_SINGLE_THREAD_NAME = "DataSourceSingleThread";

	/** The Constant PREFIX. */
	public static final String PREFIX = "Bind";

	/** The Constant SUFFIX. */
	public static final String SUFFIX = "DataSource";

	/**
	 * Instantiates a new bind data source builder.
	 *
	 * @param elementUtils
	 *            the element utils
	 * @param filer
	 *            the filer
	 * @param model
	 *            the model
	 */
	public BindDataSourceBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Generate database.
	 *
	 * @param elementUtils
	 *            the element utils
	 * @param filer
	 *            the filer
	 * @param schema
	 *            the schema
	 * @throws Exception
	 *             the exception
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
	 * Generate schema.
	 *
	 * @param schema
	 *            the schema
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void generateSchema(SQLiteDatabaseSchema schema) throws FileNotFoundException, IOException {
		// when we run in JUNIT of Kripton, we don't have to generate schemas
		// if (BindDataSourceSubProcessor.JUNIT_TEST_MODE)
		// return;
		if (!schema.generateSchema)
			return;

		// and now, write schema.create.v and schema.drop.v
		String schemaCreation = defineFileName(schema);

		String schemaLocation = KriptonOptions.getSchemaLocation();

		File schemaCreatePath = new File(schemaLocation).getAbsoluteFile();
		File schemaCreateFile = new File(schemaLocation, schemaCreation).getAbsoluteFile();
		schemaCreatePath.mkdirs();

		AnnotationProcessorUtilis.infoOnGeneratedFile(BindDataSource.class, schemaCreateFile);
		FileOutputStream fos = new FileOutputStream(schemaCreateFile);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		bw.write("------------------------------------------------------------------------------------\n");
		bw.write("--\n");
		bw.write("-- Filename: " + schemaCreation + "\n");
		bw.write("--\n");
		bw.write(String.format("-- Date: %s", (new Date()).toString()) + "\n");
		bw.write("--\n");
		if (!BindDataSourceSubProcessor.JUNIT_TEST_MODE) {
			bw.write(String.format("-- This file was generated by Kripton Annotation Processor v. %s\n",
					Version.getVersion()));
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

	/**
	 * Define file name.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	static String defineFileName(SQLiteDatabaseSchema model) {
		int lastIndex = model.fileName.lastIndexOf(".");
		String schemaName = model.fileName;

		if (lastIndex > -1) {
			schemaName = model.fileName.substring(0, lastIndex);
		}

		schemaName = schemaName.toLowerCase() + "_schema_" + model.version + ".sql";

		return schemaName;
	}

	/**
	 * Generate dataSource name.
	 *
	 * @param schema
	 *            the schema
	 * @return associated class name
	 */
	public static ClassName generateDataSourceName(SQLiteDatabaseSchema schema) {
		String dataSourceName = schema.getName();
		dataSourceName = PREFIX + dataSourceName;

		PackageElement pkg = BaseProcessor.elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? "" : pkg.getQualifiedName().toString();

		return ClassName.get(packageName, dataSourceName);
	}

	/**
	 * Builds the data source.
	 *
	 * @param elementUtils
	 *            the element utils
	 * @param filer
	 *            the filer
	 * @param schema
	 *            the schema
	 * @param daoFactoryName
	 *            the dao factory name
	 * @throws Exception
	 *             the exception
	 */
	public void buildDataSource(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema, String daoFactoryName)
			throws Exception {
		ClassName daoFactoryClazz = className(daoFactoryName);
		Converter<String, String> convert = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);

		ClassName dataSourceClassName = generateDataSourceName(schema);

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, dataSourceClassName);
		classBuilder = TypeSpec.classBuilder(dataSourceClassName.simpleName()).addModifiers(Modifier.PUBLIC)
				.superclass(AbstractDataSource.class).addSuperinterface(daoFactoryClazz)
				.addSuperinterface(TypeUtility.typeName(schema.getElement().asType()));

		classBuilder.addJavadoc("<p>\n");
		classBuilder.addJavadoc("Implementation of the $L datasource.\n", schema.getName());
		classBuilder.addJavadoc("This class expose database interface through Dao attribute.\n", schema.getName());
		classBuilder.addJavadoc("</p>\n\n");

		JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		classBuilder.addJavadoc("@see $T\n", className(schema.getName()));
		classBuilder.addJavadoc("@see $T\n", daoFactoryClazz);
		for (SQLiteDaoDefinition dao : schema.getCollection()) {
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);
			classBuilder.addJavadoc("@see $T\n", dao.getElement());
			classBuilder.addJavadoc("@see $T\n", daoImplName);

			String entity = BindDataSourceSubProcessor.generateEntityName(dao, dao.getEntity());

			classBuilder.addJavadoc("@see $T\n", TypeUtility.typeName(entity));
		}

		// define static fields

		// instance
		classBuilder.addField(FieldSpec.builder(dataSourceClassName, "instance", Modifier.STATIC, Modifier.VOLATILE)
				.addJavadoc("<p>datasource singleton</p>\n").build());
		classBuilder
				.addField(FieldSpec.builder(Object.class, "mutex", Modifier.STATIC, Modifier.FINAL, Modifier.PRIVATE)
						.addJavadoc("<p>Mutex to manage multithread access to instance</p>\n")
						.initializer("new Object()").build());

		for (SQLiteDaoDefinition dao : schema.getCollection()) {
			// TypeName daoInterfaceName =
			// BindDaoBuilder.daoInterfaceTypeName(dao);
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);
			classBuilder.addField(FieldSpec.builder(daoImplName, convert.convert(dao.getName()), Modifier.PROTECTED)
					.addJavadoc("<p>dao instance</p>\n").initializer("new $T(this)", daoImplName).build());

			// dao with connections
			{
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("get" + dao.getName())
						.addAnnotation(Override.class).addModifiers(Modifier.PUBLIC)
						.returns(BindDaoBuilder.daoTypeName(dao));
				methodBuilder.addCode("return $L;\n", convert.convert(dao.getName()));
				classBuilder.addMethod(methodBuilder.build());
			}
		}

		if (schema.generateRx) {
			generateRx(dataSourceClassName, daoFactoryName);

			for (SQLiteDaoDefinition dao : schema.getCollection()) {
				// subject
				MethodSpec.Builder methodBuilder = MethodSpec
						.methodBuilder("get" + dao.getEntitySimplyClassName() + "Subject")
						.addModifiers(Modifier.PUBLIC);
				methodBuilder
						.addStatement("return $L.getSubject()",
								CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, convert.convert(dao.getName())))
						.returns(ParameterizedTypeName.get(PublishSubject.class, SQLiteEvent.class));
				classBuilder.addMethod(methodBuilder.build());
			}
		}

		// interface
		generateMethodExecuteTransaction(daoFactoryName);

		generateMethodExecuteAsyncTransaction(daoFactoryName, true);
		generateMethodExecuteAsyncTransaction(daoFactoryName, false);

		generateMethodAsyncBatch(daoFactoryName, true);
		generateMethodAsyncBatch(daoFactoryName, false);

		generateMethodExecuteBatch(daoFactoryName);

		generateTransactions(schema);

		// generate instance
		generateInstanceOrBuild(schema, dataSourceClassName.simpleName(), true);

		// generate open
		generateOpen(dataSourceClassName.simpleName());

		// generate openReadOnly
		generateOpenReadOnly(dataSourceClassName.simpleName());

		// generate constructor
		generateConstructor(schema);

		// before use entities, order them with dependencies respect
		List<SQLiteEntity> orderedEntities = orderEntitiesList(schema);

		// onCreate
		boolean useForeignKey = generateOnCreate(schema, orderedEntities);
		//generateOnCreate(schema, orderedEntities);

		// onUpgrade
		generateOnUpgrade(schema, orderedEntities);

		// onConfigure
		// bring to AbstractDatasource
		generateHasForeignKeysNeeded(useForeignKey);

		// generate
		generateDaoUids(classBuilder, schema);

		//
		// generate prepared statement cleaner
		{

			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("clearCompiledStatements")
					.addModifiers(Modifier.PUBLIC).returns(Void.TYPE);
			for (SQLiteDaoDefinition dao : schema.getCollection()) {
				methodBuilder.addStatement("$T.clearCompiledStatements()",
						TypeUtility.className(dao.getElement().getQualifiedName().toString() + "Impl"));
			}

			classBuilder.addMethod(methodBuilder.build());
		}

		// generate single thread datasource
		generateDataSourceSingleThread(schema, dataSourceClassName.simpleName());

		// generate build
		generateInstanceOrBuild(schema, dataSourceClassName.simpleName(), false);

		{
			Builder f = FieldSpec
					.builder(ArrayTypeName.of(SQLiteTable.class), "TABLES", Modifier.FINAL, Modifier.STATIC)
					.addJavadoc("List of tables compose datasource\n");

			com.squareup.javapoet.CodeBlock.Builder c = CodeBlock.builder();
			String s = "";

			c.add("{");
			for (SQLiteEntity entity : schema.getEntities()) {
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

			classBuilder.addMethod(MethodSpec.methodBuilder("getTables").addJavadoc("List of tables compose datasource:\n")
					.addModifiers(Modifier.PUBLIC, Modifier.STATIC).addStatement("return TABLES")
					.returns(ArrayTypeName.of(SQLiteTable.class)).build());
		}

		TypeSpec typeSpec = classBuilder.build();
		JavaWriterHelper.writeJava2File(filer, dataSourceClassName.packageName(), typeSpec);
	}

	public void generateTransactions(SQLiteDatabaseSchema schema) {
		SchemaUtility.generateTransaction(classBuilder, schema, false);
	}

	/**
	 * Generate constructor.
	 *
	 * @param schema
	 *            the schema
	 */
	private void generateConstructor(SQLiteDatabaseSchema schema) {
		// constructor
		MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder()
				.addParameter(DataSourceOptions.class, "options").addModifiers(Modifier.PROTECTED);
		if (schema.generateLog) {
			methodBuilder.addStatement("super($S, $L, options)", schema.fileName, schema.version);
		} else {
			methodBuilder.addStatement("super($S, $L, $T.builder().createFrom(options).log(false).build())",
					schema.fileName, schema.version, DataSourceOptions.class);
		}

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate Dao's UID. If specified, prefix will be used to
	 *
	 * @param classBuilder
	 *            the class builder
	 * @param schema
	 *            the schema
	 */
	public static void generateDaoUids(TypeSpec.Builder classBuilder, SQLiteDatabaseSchema schema) {

		for (SQLiteDaoDefinition dao : schema.getCollection()) {

			classBuilder.addField(
					FieldSpec.builder(Integer.TYPE, dao.daoUidName, Modifier.FINAL, Modifier.STATIC, Modifier.PUBLIC)
							.initializer("" + dao.daoUidValue)
							.addJavadoc("Unique identifier for Dao $L\n", dao.getName()).build());
		}
	}

	/**
	 * Generate data source single thread.
	 *
	 * @param schema
	 *            the schema
	 * @param dataSourceName
	 *            the data source name
	 */
	private void generateDataSourceSingleThread(SQLiteDatabaseSchema schema, String dataSourceName) {
		// class DataSourceSingleThread
		String daoFactoryName = BindDaoFactoryBuilder.generateDaoFactoryName(schema);
		TypeSpec.Builder clazzBuilder = TypeSpec.classBuilder(DATA_SOURCE_SINGLE_THREAD_NAME)
				.addSuperinterface(TypeUtility.typeName(daoFactoryName));

		// _context field
		clazzBuilder.addField(FieldSpec.builder(SQLContextInSessionImpl.class, "_context", Modifier.PRIVATE).build());

		// constructor
		MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder();
		constructorBuilder.addStatement("_context=new $T($L.this)", SQLContextInSessionImpl.class, dataSourceName);

		// all dao
		for (SQLiteDaoDefinition dao : schema.getCollection()) {
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);

			// dao with external connections
			{
				String daoFieldName = extractDaoFieldNameForInternalDataSource(dao);
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("get" + dao.getName())
						.addModifiers(Modifier.PUBLIC).addJavadoc("\nretrieve dao $L\n", dao.getName())
						.returns(daoImplName);
				methodBuilder.beginControlFlow("if ($L==null)", daoFieldName);
				methodBuilder.addStatement("$L=new $T(this)", daoFieldName, daoImplName);
				methodBuilder.endControlFlow();
				methodBuilder.addStatement("return $L", daoFieldName);
				clazzBuilder.addMethod(methodBuilder.build());

				clazzBuilder.addField(FieldSpec.builder(daoImplName, daoFieldName, Modifier.PROTECTED).build());
			}
		}

		// constructor
		clazzBuilder.addMethod(constructorBuilder.build());

		// public SQLContext context()
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("getContext").addModifiers(Modifier.PUBLIC)
					.returns(SQLContext.class);
			methodBuilder.addAnnotation(Override.class);
			methodBuilder.addStatement("return _context");
			clazzBuilder.addMethod(methodBuilder.build());
		}

		// onSessionOpened
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onSessionOpened")
					.addModifiers(Modifier.PROTECTED).returns(Void.TYPE);
			if (schema.hasLiveData()) {
				methodBuilder.addComment("support for live data");
				methodBuilder.addStatement("_context.onSessionOpened()");
			}

			clazzBuilder.addMethod(methodBuilder.build());
		}

		// onSessionClear
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onSessionClear")
					.addModifiers(Modifier.PROTECTED).returns(Void.TYPE);
			if (schema.hasLiveData()) {
				methodBuilder.addComment("support for live data");
				methodBuilder.addStatement("_context.onSessionOpened()");
			}

			clazzBuilder.addMethod(methodBuilder.build());
		}

		// onSessionClosed
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onSessionClosed")
					.addModifiers(Modifier.PROTECTED).returns(Void.TYPE);

			if (schema.hasLiveData()) {
				methodBuilder.addComment("support for live data");
				methodBuilder.addStatement("$T daosWithEvents=_context.onSessionClosed()",
						ParameterizedTypeName.get(Set.class, Integer.class));

				for (SQLiteDaoDefinition dao : schema.getCollection()) {

					String daoFieldName = extractDaoFieldNameForInternalDataSource(dao);
					if (dao.hasLiveData()) {
						methodBuilder.beginControlFlow("if ($L!=null && daosWithEvents.contains($L))", daoFieldName,
								dao.daoUidName);
						methodBuilder.addStatement("$L.invalidateLiveData()", daoFieldName);
						methodBuilder.endControlFlow();
					} else {
						methodBuilder.addComment("$S has no live data", daoFieldName);
					}
				}
			}

			clazzBuilder.addMethod(methodBuilder.build());
		}

		// build method bindToThread
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bindToThread").addModifiers(Modifier.PUBLIC)
					.returns(TypeUtility.typeName(DATA_SOURCE_SINGLE_THREAD_NAME));

			// methodBuilder.addStatement("_context.bindToThread()");
			methodBuilder.addStatement("return this");
			clazzBuilder.addMethod(methodBuilder.build());

			// build single thread daoFactory used in transaction
			classBuilder.addField(FieldSpec
					.builder(TypeUtility.typeName(DATA_SOURCE_SINGLE_THREAD_NAME), "_daoFactorySingleThread",
							Modifier.PROTECTED)
					.addJavadoc("Used only in transactions (that can be executed one for time\n")
					.initializer("new DataSourceSingleThread()").build());
		}

		// build transactions
		{
			SchemaUtility.generateTransaction(clazzBuilder, schema, false);
		}

		classBuilder.addType(clazzBuilder.build());

	}

	/**
	 * Extract dao field name for internal data source.
	 *
	 * @param dao
	 *            the dao
	 * @return the string
	 */
	private String extractDaoFieldNameForInternalDataSource(SQLiteDaoDefinition dao) {
		return "_" + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, dao.getName());
	}

	/**
	 * Generate inner code for instance and build methods. Inspired by <a href=
	 * "https://www.journaldev.com/171/thread-safety-in-java-singleton-classes-with-example-code">this
	 * link<</a>
	 *
	 * @param schema
	 *            the schema
	 * @param schemaName
	 *            the schema name
	 * @param instance
	 *            the instance
	 */
	private void generateInstanceOrBuild(SQLiteDatabaseSchema schema, String schemaName, boolean instance) {
		// instance
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(instance ? "getInstance" : "build")
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(className(schemaName));

		if (!instance) {
			methodBuilder.addParameter(DataSourceOptions.class, "options");
			methodBuilder.addJavadoc(
					"<p>Build instance. This method can be used only one time, on the application start.</p>\n");
		} else {
			methodBuilder.addJavadoc("<p>Retrieve instance.</p>\n");
		}

		methodBuilder.addStatement("$T result=instance", className(schemaName));
		methodBuilder.beginControlFlow("if (result==null)");
		methodBuilder.beginControlFlow("synchronized(mutex)");
		methodBuilder.addStatement("result=instance");
		methodBuilder.beginControlFlow("if (result==null)");

		if (instance) {
			methodBuilder.addCode("$T options=$T.builder()", DataSourceOptions.class, DataSourceOptions.class);

			if (schema.configOpenHelperFactoryClazz != null) {
				methodBuilder.addCode("\n\t.openHelperFactory(new $T())",
						TypeUtility.className(schema.configOpenHelperFactoryClazz));
			}
			if (schema.configDatabaseErrorHandlerClazz != null) {
				methodBuilder.addCode("\n\t.errorHandler(new $T())",
						TypeUtility.className(schema.configDatabaseErrorHandlerClazz));
			}
			if (schema.configDatabaseLifecycleHandlerClazz != null) {
				methodBuilder.addCode("\n\t.databaseLifecycleHandler(new $T())",
						TypeUtility.className(schema.configDatabaseLifecycleHandlerClazz));
			}
			if (schema.configPopulatorClazz != null) {
				methodBuilder.addCode("\n\t.populator(new $T())", TypeUtility.className(schema.configPopulatorClazz));
			}

			methodBuilder.addCode("\n\t.inMemory($L)", schema.configInMemory);

			methodBuilder.addCode("\n\t.log($L)", schema.configLogEnabled);

			if (schema.configUpdateTasks != null && schema.configUpdateTasks.size() > 0) {
				for (Pair<Integer, String> task : schema.configUpdateTasks) {
					methodBuilder.addCode("\n\t.addUpdateTask($L, new $T())", task.value0,
							TypeUtility.className(task.value1));
				}
			}

			methodBuilder.addCode("\n\t.build();\n", DataSourceOptions.class, DataSourceOptions.class);
		}
		methodBuilder.addStatement("instance=result=new $L(options)", schemaName);

		generatePopulate(schema, methodBuilder, instance);

		if (!instance) {
			methodBuilder.nextControlFlow("else");
			methodBuilder.addStatement("throw new $T($S)", KriptonRuntimeException.class,
					"Datasource " + schemaName + " is already builded");
		}
		methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();
		if (!instance) {
			methodBuilder.nextControlFlow("else");
			methodBuilder.addStatement("throw new $T($S)", KriptonRuntimeException.class,
					"Datasource " + schemaName + " is already builded");
		}
		methodBuilder.endControlFlow();

		methodBuilder.addCode("return result;\n");

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate populate.
	 *
	 * @param schema
	 *            the schema
	 * @param methodBuilder
	 *            the method builder
	 * @param instance
	 *            the instance
	 */
	private void generatePopulate(SQLiteDatabaseSchema schema, MethodSpec.Builder methodBuilder, boolean instance) {

		methodBuilder.beginControlFlow("try");
		methodBuilder.addStatement("instance.openWritableDatabase()");
		methodBuilder.addStatement("instance.close()");

		if ((instance && schema.configPopulatorClazz != null) || (!instance)) {
			methodBuilder.addComment("force database DDL run");
			methodBuilder.beginControlFlow("if (options.populator!=null && instance.justCreated)");
			methodBuilder.addComment("run populator only a time");
			methodBuilder.addStatement("instance.justCreated=false");

			// populator manage its connection			
			methodBuilder.addComment("run populator");
			methodBuilder.addStatement("options.populator.execute()");

			// methodBuilder.nextControlFlow("finally");
			// methodBuilder.addStatement("instance.close()");
			// methodBuilder.endControlFlow();

			methodBuilder.endControlFlow();
		}
		methodBuilder.nextControlFlow("catch($T e)", Throwable.class);
		methodBuilder.addStatement("$T.error(e.getMessage())", Logger.class);
		methodBuilder.addStatement("e.printStackTrace()");

		methodBuilder.endControlFlow();
	}

	/**
	 * Generate open.
	 *
	 * @param schemaName
	 *            the schema name
	 */
	private void generateOpen(String schemaName) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("open")
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(className(schemaName));

		methodBuilder.addJavadoc("Retrieve data source instance and open it.\n");
		methodBuilder.addJavadoc("@return opened dataSource instance.\n");

		methodBuilder.addStatement("$L instance=getInstance()", schemaName);

		methodBuilder.addStatement("instance.openWritableDatabase()");
		methodBuilder.addCode("return instance;\n");

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate open read only.
	 *
	 * @param schemaName
	 *            the schema name
	 */
	private void generateOpenReadOnly(String schemaName) {
		// instance
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("openReadOnly")
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(className(schemaName));

		methodBuilder.addJavadoc("Retrieve data source instance and open it in read only mode.\n");
		methodBuilder.addJavadoc("@return opened dataSource instance.\n");

		methodBuilder.addStatement("$L instance=getInstance()", schemaName);

		methodBuilder.addStatement("instance.openReadOnlyDatabase()");
		methodBuilder.addCode("return instance;\n");

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate on create.
	 *
	 * @param schema
	 *            the schema
	 * @param orderedEntities
	 *            the ordered entities
	 * @return true, if successful
	 */
	private boolean generateOnCreate(SQLiteDatabaseSchema schema, List<SQLiteEntity> orderedEntities) {
		boolean useForeignKey = false;
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onCreate").addAnnotation(Override.class)
				.addModifiers(Modifier.PROTECTED);
		methodBuilder.addParameter(KriptonDynamicClassManager.getInstance().getDatabaseClazz(), "database");
		methodBuilder.addJavadoc("onCreate\n");
		methodBuilder.addCode("// generate tables\n");
		if (schema.isLogEnabled()) {
			// generate log section - BEGIN
			methodBuilder.addComment("log section create BEGIN");
			methodBuilder.beginControlFlow("if (this.logEnabled)");

			methodBuilder.beginControlFlow("if (options.inMemory)");
			methodBuilder.addStatement("$T.info(\"Create database in memory\")", Logger.class);
			methodBuilder.nextControlFlow("else");
			methodBuilder.addStatement("$T.info(\"Create database '%s' version %s\",this.name, this.version)",
					Logger.class);
			methodBuilder.endControlFlow();

			// generate log section - END
			methodBuilder.endControlFlow();
			methodBuilder.addComment("log section create END");
		}
		for (SQLiteEntity item : orderedEntities) {
			if (schema.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section create BEGIN");
				methodBuilder.beginControlFlow("if (this.logEnabled)");

				methodBuilder.addStatement("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL)", Logger.class,
						BindTableGenerator.tableClassName(null, item));

				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section create END");
			}
			methodBuilder.addStatement("database.execSQL($T.CREATE_TABLE_SQL)",
					BindTableGenerator.tableClassName(null, item));

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

				methodBuilder.addStatement("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL)", Logger.class,
						TypeUtility.className(BindTableGenerator.getTableClassName(item.getQualifiedName())));

				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}
			methodBuilder.addStatement("database.execSQL($T.CREATE_TABLE_SQL)",
					TypeUtility.className(BindTableGenerator.getTableClassName(item.getQualifiedName())));

		}

		methodBuilder.beginControlFlow("if (options.databaseLifecycleHandler != null)");
		methodBuilder.addStatement("options.databaseLifecycleHandler.onCreate(database)");
		methodBuilder.endControlFlow();

		methodBuilder.addStatement("justCreated=true");

		classBuilder.addMethod(methodBuilder.build());

		return useForeignKey;
	}

	/**
	 * Generate on upgrade.
	 *
	 * @param schema
	 *            the schema
	 * @param orderedEntities
	 *            the ordered entities
	 */
	private void generateOnUpgrade(SQLiteDatabaseSchema schema, List<SQLiteEntity> orderedEntities) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onUpgrade").addAnnotation(Override.class)
				.addModifiers(Modifier.PROTECTED);
		methodBuilder.addParameter(KriptonDynamicClassManager.getInstance().getDatabaseClazz(), "database");
		methodBuilder.addParameter(Integer.TYPE, "previousVersion");
		methodBuilder.addParameter(Integer.TYPE, "currentVersion");
		methodBuilder.addJavadoc("onUpgrade\n");

		Collections.reverse(orderedEntities);

		if (schema.isLogEnabled()) {
			// generate log section - BEGIN
			methodBuilder.addComment("log section BEGIN");
			methodBuilder.beginControlFlow("if (this.logEnabled)");

			methodBuilder.addStatement(
					"$T.info(\"Update database '%s' from version %s to version %s\",this.name, previousVersion, currentVersion)",
					Logger.class);

			// generate log section - END
			methodBuilder.endControlFlow();
			methodBuilder.addComment("log section END");
		}

		methodBuilder.addComment("if we have a list of update task, try to execute them");
		methodBuilder.beginControlFlow("if (options.updateTasks != null)");
		methodBuilder.addStatement("$T<$T> tasks = buildTaskList(previousVersion, currentVersion)", List.class,
				SQLiteUpdateTask.class);
		methodBuilder.beginControlFlow("for ($T task : tasks)", SQLiteUpdateTask.class);

		// generate log section - BEGIN
		methodBuilder.addComment("log section BEGIN");
		methodBuilder.beginControlFlow("if (this.logEnabled)");

		methodBuilder.addStatement(
				"$T.info(\"Begin update database from version %s to %s\", previousVersion, previousVersion+1)",
				Logger.class);

		// generate log section - END
		methodBuilder.endControlFlow();
		methodBuilder.addComment("log section END");

		methodBuilder.addStatement("task.execute(database, previousVersion, previousVersion+1)");

		// generate log section - BEGIN
		methodBuilder.addComment("log section BEGIN");
		methodBuilder.beginControlFlow("if (this.logEnabled)");

		methodBuilder.addStatement(
				"$T.info(\"End update database from version %s to %s\", previousVersion, previousVersion+1)",
				Logger.class);

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

		for (SQLiteEntity item : orderedEntities) {
			if (schema.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (this.logEnabled)");

				methodBuilder.addCode("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL);\n", Logger.class,
						BindTableGenerator.tableClassName(null, item));

				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}
			methodBuilder.addCode("database.execSQL($T.CREATE_TABLE_SQL);\n",
					BindTableGenerator.tableClassName(null, item));
		}

		// use generated entities too
		for (GeneratedTypeElement item : schema.generatedEntities) {
			if (schema.isLogEnabled()) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (this.logEnabled)");
				methodBuilder.addStatement("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL)", Logger.class,
						TypeUtility.className(BindTableGenerator.getTableClassName(item.getQualifiedName())));
				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}
			methodBuilder.addStatement("database.execSQL($T.CREATE_TABLE_SQL)",
					TypeUtility.className(BindTableGenerator.getTableClassName(item.getQualifiedName())));
		}

		methodBuilder.endControlFlow();

		methodBuilder.beginControlFlow("if (options.databaseLifecycleHandler != null)");
		methodBuilder.addStatement(
				"options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true)");
		methodBuilder.endControlFlow();

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate on configure.
	 *
	 * @param useForeignKey
	 *            the use foreign key
	 */
	private void generateHasForeignKeysNeeded(boolean useForeignKey) {
		// onConfigure

		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("hasForeignKeys").addAnnotation(Override.class)
				.returns(Boolean.TYPE)
				.addModifiers(Modifier.PUBLIC);
		//methodBuilder.addParameter(KriptonDynamicClassManager.getInstance().getDatabaseClazz(), "database");
		methodBuilder.addJavadoc("Returns <code>true</code> if database needs foreign keys.\n");
		
		methodBuilder.addStatement("return $L", useForeignKey);
		/*if (useForeignKey) {
			
		}*/
		
		//methodBuilder.addCode("// configure database\n");

		/*if (useForeignKey) {
			methodBuilder.addStatement("database.setForeignKeyConstraintsEnabled(true)");
		}

		methodBuilder.beginControlFlow("if (options.databaseLifecycleHandler != null)");
		methodBuilder.addStatement("options.databaseLifecycleHandler.onConfigure(database)");
		methodBuilder.endControlFlow();*/

		classBuilder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate ordered entities list.
	 *
	 * @param schema
	 *            the schema
	 * @return the list
	 */
	public static List<SQLiteEntity> orderEntitiesList(SQLiteDatabaseSchema schema) {
		List<SQLiteEntity> entities = schema.getEntitiesAsList();
		Collections.sort(entities, new Comparator<SQLiteEntity>() {

			@Override
			public int compare(SQLiteEntity lhs, SQLiteEntity rhs) {
				return lhs.getTableName().compareTo(rhs.getTableName());
			}
		});

		List<SQLiteEntity> list = schema.getEntitiesAsList();

		EntitySorter<SQLiteEntity> sorder = new EntitySorter<SQLiteEntity>(list) {

			@Override
			public Collection<SQLiteEntity> getDependencies(SQLiteEntity item) {
				return item.referedEntities;
			}

			@Override
			public void generateError(SQLiteEntity item) {
				throw new CircularRelationshipException(item);
			}
		};

		return sorder.order();
	}

	/**
	 * Generat execute transaction rx.
	 *
	 * @param dataSourceName
	 *            the data source name
	 * @param daoFactory
	 *            the dao factory
	 * @param rxType
	 *            the rx type
	 */
	public void generatExecuteTransactionRx(ClassName dataSourceName, String daoFactory, RxType rxType) {
		String parameterName = "transaction";
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

						// lock the database
						.addComment("open database in thread safe mode")
						.addStatement("$T<Boolean, $T> _status=$L.this.openDatabaseThreadSafeMode(true)", Pair.class,
								KriptonDynamicClassManager.getInstance().getDatabaseClazz(),
								dataSourceName.simpleName())

						.addStatement("boolean success=false")// .addCode("@SuppressWarnings(\"resource\")\n")
						.addStatement("$T connection=_status.value1",
								KriptonDynamicClassManager.getInstance().getDatabaseClazz())

						// support for live data
						.addStatement("$L currentDaoFactory=_daoFactorySingleThread.bindToThread()",
								DATA_SOURCE_SINGLE_THREAD_NAME)
						.addStatement("currentDaoFactory.onSessionOpened()")

						.beginControlFlow("try").addStatement("connection.beginTransaction()")
						.beginControlFlow(
								"if (transaction != null && $T.$L==transaction.onExecute(currentDaoFactory, emitter))",
								TransactionResult.class, TransactionResult.COMMIT)
						.addStatement("connection.setTransactionSuccessful()").addStatement("success=true")
						.endControlFlow().addStatement(rxType.onComplete ? "emitter.onComplete()" : "// no onComplete")

						.nextControlFlow("catch($T e)", Throwable.class)
						.addStatement("$T.error(e.getMessage())", Logger.class).addStatement("e.printStackTrace()")
						.addStatement("emitter.onError(e)")
						// support for live data
						.addStatement("currentDaoFactory.onSessionClear()").nextControlFlow("finally")
						.beginControlFlow("try").addStatement("connection.endTransaction()")
						.nextControlFlow("catch($T e)", Throwable.class).endControlFlow()
						// lock the database
						.addComment("close database in thread safe mode").addStatement("closeThreadSafeMode(_status)")
						// support for live data
						.addCode(
								"if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }\n")
						.endControlFlow().addStatement("return").build())
				.build();

		{
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC)
					.addTypeVariable(TypeVariableName.get("T"))
					.addParameter(
							ParameterizedTypeName.get(TypeUtility.className(dataSourceName.toString(),
									rxType.clazz.getSimpleName() + "Transaction"), TypeVariableName.get("T")),
							parameterName, Modifier.FINAL)
					.returns(returnTypeName);

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

	}

	/**
	 * Generat execute batch rx.
	 *
	 * @param dataSourceName
	 *            the data source name
	 * @param daoFactory
	 *            the dao factory
	 * @param rxType
	 *            the rx type
	 */
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

						// lock the database
						.addComment("open database in thread safe mode")
						.addStatement("$T<Boolean, $T> _status=$L.this.openDatabaseThreadSafeMode(true)", Pair.class,
								KriptonDynamicClassManager.getInstance().getDatabaseClazz(),
								dataSourceName.simpleName())

						// support for live data
						.addStatement("$L currentDaoFactory=new DataSourceSingleThread()",
								DATA_SOURCE_SINGLE_THREAD_NAME)
						.addStatement("currentDaoFactory.onSessionOpened()")

						.beginControlFlow("try")
						.addCode("if ($L != null) { $L.onExecute(currentDaoFactory, emitter); }\n", parameterName,
								parameterName)
						.addStatement(rxType.onComplete ? "emitter.onComplete()" : "// no onComplete")
						.nextControlFlow("catch($T e)", Throwable.class)
						.addStatement("$T.error(e.getMessage())", Logger.class).addStatement("e.printStackTrace()")
						.addStatement("emitter.onError(e)").nextControlFlow("finally")

						// lock the database
						.addComment("close database in thread safe mode").addStatement("closeThreadSafeMode(_status)")

						// support for live data
						.addStatement("currentDaoFactory.onSessionClosed()").endControlFlow().addStatement("return")
						.build())
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
	 * .
	 *
	 * @param dataSourceName
	 *            the data source name
	 * @param daoFactory
	 *            the dao factory
	 */
	public void generateRx(ClassName dataSourceName, String daoFactory) {
		classBuilder.addField(FieldSpec.builder(Scheduler.class, "globalSubscribeOn", Modifier.PROTECTED).build());
		classBuilder.addMethod(MethodSpec.methodBuilder("globalSubscribeOn").returns(dataSourceName)
				.addParameter(Scheduler.class, "scheduler").addModifiers(Modifier.PUBLIC)
				.addStatement("this.globalSubscribeOn=scheduler").addStatement("return this").build());

		classBuilder.addField(FieldSpec.builder(Scheduler.class, "globalObserveOn", Modifier.PROTECTED).build());
		classBuilder.addMethod(MethodSpec.methodBuilder("globalObserveOn").addParameter(Scheduler.class, "scheduler")
				.returns(dataSourceName).addModifiers(Modifier.PUBLIC).addStatement("this.globalObserveOn=scheduler")
				.addStatement("return this").build());

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

	/**
	 * The Enum RxInterfaceType.
	 */
	private enum RxInterfaceType {

		/** The batch. */
		BATCH,
		/** The transaction. */
		TRANSACTION
	}

	/**
	 * The Enum RxType.
	 */
	private enum RxType {

		/** The observable. */
		OBSERVABLE(Observable.class, true),
		/** The single. */
		SINGLE(Single.class, false),
		/** The maybe. */
		MAYBE(Maybe.class, false),
		/** The flowable. */
		FLOWABLE(Flowable.class, true);

		/**
		 * Instantiates a new rx type.
		 *
		 * @param clazz
		 *            the clazz
		 * @param onComplete
		 *            the on complete
		 */
		private RxType(Class<?> clazz, boolean onComplete) {
			this.clazz = clazz;
			this.onComplete = onComplete;
		}

		/** The clazz. */
		public Class<?> clazz;

		/** The on complete. */
		public boolean onComplete;

	}

	/**
	 * Generate rx interface.
	 *
	 * @param daoFactory
	 *            the dao factory
	 * @param interfaceType
	 *            the interface type
	 * @param clazz
	 *            the clazz
	 */
	private void generateRxInterface(String daoFactory, RxInterfaceType interfaceType, Class<?> clazz) {
		// create interfaces
		{
			ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(clazz),
					TypeVariableName.get("T"));
			String preExecutorName = clazz.getSimpleName().replace("Emitter", "");
			String postExecutorName = com.abubusoft.kripton.common.CaseFormat.UPPER_UNDERSCORE
					.to(CaseFormat.UPPER_CAMEL, interfaceType.toString());
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

	private void generateMethodAsyncBatch(String daoFactory, boolean withErrorListener) {
		// create interface
		String transationExecutorName = "Batch";
		MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("executeBatchAsync").addModifiers(Modifier.PUBLIC)
				.addTypeVariable(TypeVariableName.get("T"))
				.returns(ParameterizedTypeName.get(ClassName.get(Future.class), TypeVariableName.get("T")))
				.addParameter(ParameterizedTypeName.get(className(transationExecutorName), TypeVariableName.get("T")),
						"commands", Modifier.FINAL);

		if (withErrorListener) {
			executeMethod.addParameter(Boolean.TYPE, "writeMode", Modifier.FINAL);
		}

		ParameterizedTypeName futureType = ParameterizedTypeName.get(ClassName.get(Callable.class),
				TypeVariableName.get("T"));
		TypeSpec innerBuilder = TypeSpec.anonymousClassBuilder("").addSuperinterface(futureType)
				.addMethod(MethodSpec.methodBuilder("call").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class)
						.returns(TypeVariableName.get("T")).addException(Exception.class).addStatement(withErrorListener
								? "return executeBatch(commands, writeMode)" : "return executeBatch(commands, false)")
						.build())
				.build();

		executeMethod.addStatement("return $T.getExecutorService().submit($L)", KriptonLibrary.class, innerBuilder);

		{
			// generate javadoc
			executeMethod.addJavadoc(
					"<p>Executes a batch command in async mode. This method <strong>is thread safe</strong> to avoid concurrent problems. The "
							+ "drawback is only one transaction at time can be executed. The database will be open in write mode. This method uses default error listener to intercept errors.</p>\n");
			executeMethod.addJavadoc("\n");
			executeMethod.addJavadoc("@param commands\n\tcommands to execute\n");
			if (withErrorListener) {
				executeMethod.addJavadoc("@param writeMode\n\true if you need to writeable connection\n");
			}
			executeMethod.addJavadoc("@return <code>true</code> when transaction successful finished\n");

			classBuilder.addMethod(executeMethod.build());
		}
	}

	private void generateMethodExecuteAsyncTransaction(String daoFactory, boolean withErrorListener) {
		// create interface
		String transationExecutorName = "Transaction";
		MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("executeAsync").addModifiers(Modifier.PUBLIC)
				.returns(ParameterizedTypeName.get(Future.class, Boolean.class))
				.addParameter(className(transationExecutorName), "transaction", Modifier.FINAL);

		if (withErrorListener) {
			executeMethod.addParameter(AbstractDataSource.OnErrorListener.class, "onErrorListener", Modifier.FINAL);
		}

		ParameterizedTypeName futureType = ParameterizedTypeName.get(Callable.class, Boolean.class);
		TypeSpec innerBuilder = TypeSpec.anonymousClassBuilder("").addSuperinterface(futureType)
				.addMethod(MethodSpec.methodBuilder("call").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class)
						.returns(Boolean.class).addException(Exception.class)
						.addStatement("return execute(transaction, onErrorListener)").build())
				.build();

		executeMethod.addStatement("return $T.getExecutorService().submit($L)", KriptonLibrary.class, innerBuilder);

		{

			// generate javadoc
			executeMethod.addJavadoc(
					"<p>Executes a transaction in async mode. This method <strong>is thread safe</strong> to avoid concurrent problems. The "
							+ "drawback is only one transaction at time can be executed. The database will be open in write mode. This method uses default error listener to intercept errors.</p>\n");
			executeMethod.addJavadoc("\n");
			executeMethod.addJavadoc("@param transaction\n\ttransaction to execute\n");
			if (withErrorListener) {
				executeMethod.addJavadoc("@param onErrorListener\n\tlistener for errors\n");
			}
			executeMethod.addJavadoc("@return <code>true</code> when transaction successful finished\n");

			classBuilder.addMethod(executeMethod.build());
		}
	}

	/**
	 * <p>
	 * Generate transaction an execute method
	 * </p>
	 * .
	 *
	 * @param daoFactory
	 *            the dao factory
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
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC)
					.returns(Boolean.TYPE).addParameter(className(transationExecutorName), "transaction");
			// generate javadoc
			executeMethod.addJavadoc(
					"<p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The "
							+ "drawback is only one transaction at time can be executed. The database will be open in write mode. This method uses default error listener to intercept errors.</p>\n");
			executeMethod.addJavadoc("\n");
			executeMethod.addJavadoc("@param transaction\n\ttransaction to execute\n");
			executeMethod.addJavadoc("@return <code>true</code> if transaction successful finished\n");

			executeMethod.addStatement("return execute(transaction, onErrorListener)");

			classBuilder.addMethod(executeMethod.build());
		}

		{
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC)
					.addParameter(className(transationExecutorName), "transaction").returns(Boolean.TYPE)
					.addParameter(className(OnErrorListener.class), "onErrorListener");

			// lock the database
			executeMethod.addComment("open database in thread safe mode");
			executeMethod.addStatement("$T<Boolean, $T> _status=openDatabaseThreadSafeMode(true)", Pair.class,
					KriptonDynamicClassManager.getInstance().getDatabaseClazz());

			executeMethod.addStatement("boolean success=false");
			// executeMethod.addCode("@SuppressWarnings(\"resource\")\n");
			executeMethod.addStatement("$T connection=_status.value1",
					KriptonDynamicClassManager.getInstance().getDatabaseClazz());

			// support for live data
			executeMethod.addStatement("$L currentDaoFactory=_daoFactorySingleThread.bindToThread()",
					DATA_SOURCE_SINGLE_THREAD_NAME);
			executeMethod.addStatement("currentDaoFactory.onSessionOpened()");

			executeMethod.beginControlFlow("try");
			executeMethod.addCode("connection.beginTransaction();\n");

			executeMethod.beginControlFlow(
					"if (transaction!=null && $T.$L == transaction.onExecute(currentDaoFactory))",
					TransactionResult.class, TransactionResult.COMMIT);
			executeMethod.addStatement("connection.setTransactionSuccessful()");

			// support for live data
			executeMethod.addStatement("success=true");

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

			// lock the database
			executeMethod.addComment("close database in thread safe mode");
			executeMethod.addStatement("closeThreadSafeMode(_status)");

			// support for live data
			executeMethod.addCode(
					"if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }\n");
			executeMethod.endControlFlow();
			executeMethod.addStatement("return success");

			// generate javadoc
			executeMethod.addJavadoc(
					"<p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The "
							+ "drawback is only one transaction at time can be executed. The database will be open in write mode.</p>\n");
			executeMethod.addJavadoc("\n");
			executeMethod.addJavadoc("@param transaction\n\ttransaction to execute\n");
			executeMethod.addJavadoc("@param onErrorListener\n\terror listener\n");
			executeMethod.addJavadoc("@return <code>true</code> if transaction successful finished\n");

			classBuilder.addMethod(executeMethod.build());
		}

	}

	/**
	 * <p>
	 * Generate transaction an execute method
	 * </p>
	 * .
	 *
	 * @param daoFactory
	 *            the dao factory
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
					.addJavadoc(
							"<p>Executes a batch opening a read only connection. This method <strong>is thread safe</strong> to avoid concurrent problems.</p>\n\n")
					.addJavadoc("@param commands\n\tbatch to execute\n").addModifiers(Modifier.PUBLIC)
					.addParameter(
							ParameterizedTypeName.get(className(transationExecutorName), TypeVariableName.get("T")),
							"commands")
					.returns(TypeVariableName.get("T"));

			executeMethod.addStatement("return executeBatch(commands, false)");
			classBuilder.addMethod(executeMethod.build());
		}

		{
			// execute read/write mode
			MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("executeBatch")
					// generate javadoc
					.addJavadoc(
							"<p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. The "
									+ "drawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>\n")
					.addTypeVariable(TypeVariableName.get("T")).addJavadoc("\n")
					.addJavadoc("@param commands\n\tbatch to execute\n")
					.addJavadoc(
							"@param writeMode\n\ttrue to open connection in write mode, false to open connection in read only mode\n")
					.addModifiers(Modifier.PUBLIC)
					.addParameter(
							ParameterizedTypeName.get(className(transationExecutorName), TypeVariableName.get("T")),
							"commands")
					.addParameter(Boolean.TYPE, "writeMode").returns(TypeVariableName.get("T"));

			// lock the database
			executeMethod.addComment("open database in thread safe mode");
			executeMethod.addStatement("$T<Boolean, $T> _status=openDatabaseThreadSafeMode(writeMode)", Pair.class,
					KriptonDynamicClassManager.getInstance().getDatabaseClazz());

			// support for live data
			executeMethod.addStatement("$L currentDaoFactory=new DataSourceSingleThread()",
					DATA_SOURCE_SINGLE_THREAD_NAME);
			executeMethod.addStatement("currentDaoFactory.onSessionOpened()");

			executeMethod.beginControlFlow("try");
			executeMethod.beginControlFlow("if (commands!=null)");
			executeMethod.addStatement("return commands.onExecute(currentDaoFactory)");

			executeMethod.endControlFlow();

			executeMethod.nextControlFlow("catch($T e)", Throwable.class);

			executeMethod.addStatement("$T.error(e.getMessage())", Logger.class);
			executeMethod.addStatement("e.printStackTrace()");
			executeMethod.addStatement("throw(e)");

			executeMethod.nextControlFlow("finally");

			// lock the database
			executeMethod.addComment("close database in thread safe mode");
			executeMethod.addStatement("closeThreadSafeMode(_status)");

			// support for live data
			executeMethod.addStatement("currentDaoFactory.onSessionClosed()");
			executeMethod.endControlFlow();
			executeMethod.addStatement("return null");

			classBuilder.addMethod(executeMethod.build());
		}
	}

}
