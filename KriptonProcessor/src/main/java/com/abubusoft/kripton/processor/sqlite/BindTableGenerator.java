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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import org.apache.commons.lang3.StringUtils;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper.PersistType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelElementVisitor;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.element.GeneratedTypeElement;
import com.abubusoft.kripton.processor.exceptions.InvalidBeanTypeException;
import com.abubusoft.kripton.processor.exceptions.InvalidForeignKeyTypeException;
import com.abubusoft.kripton.processor.exceptions.NoDaoElementFound;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.FieldSpec.Builder;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * <p>
 * Generate class ${entity}Table which represents table for entity.
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindTableGenerator extends AbstractBuilder implements ModelElementVisitor<SQLEntity, SQLProperty> {

	public static final String SUFFIX = "Table";

	private Converter<String, String> columnNameToUpperCaseConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

	public BindTableGenerator(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Generate table for entities
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * @param generatedEntities
	 * @throws Exception
	 */
	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema, Set<GeneratedTypeElement> generatedEntities) throws Exception {
		BindTableGenerator visitor = new BindTableGenerator(elementUtils, filer, schema);

		for (SQLEntity item : schema.getEntities()) {
			visitor.visit(schema, item);
		}

		// generate table for generated entity
		for (GeneratedTypeElement genItem : generatedEntities) {
			visitor.visit(schema, genItem);
		}
	}

	private void visit(SQLiteDatabaseSchema schema, GeneratedTypeElement entity) throws Exception {
		int indexCounter = 0;

		// generate the class name that represents the table
		String classTableName = getTableClassName(entity.getSimpleName());

		PackageElement pkg = elementUtils.getPackageElement(entity.packageName);
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, classTableName);
		classBuilder = TypeSpec.classBuilder(classTableName).addModifiers(Modifier.PUBLIC);

		BindTypeContext context = new BindTypeContext(classBuilder, TypeUtility.typeName(packageName, classTableName), Modifier.STATIC, Modifier.PRIVATE);

		// javadoc for class
		classBuilder.addJavadoc("<p>");
		classBuilder.addJavadoc("\nEntity <code>$L</code> is associated to table <code>$L</code>\n", entity.getSimpleName(), entity.getTableName());
		classBuilder.addJavadoc("This class represents table associated to entity.\n");
		classBuilder.addJavadoc("</p>\n");
		JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		classBuilder.addJavadoc(" @see $T\n", TypeUtility.className(entity.getName()));

		{
			//@formatter:off
			// table_name
			FieldSpec fieldSpec = FieldSpec.builder(String.class, "TABLE_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
					.initializer("\"$L\"", entity.getTableName())
					.addJavadoc("Costant represents typeName of table $L\n",entity.getTableName())
					.build();
			classBuilder.addField(fieldSpec);
			//@formatter:on
		}

		StringBuilder bufferTable = new StringBuilder();
		StringBuilder bufferForeignKey = new StringBuilder();
		// shared between create table and drop table
		StringBuilder bufferIndexesCreate = new StringBuilder();
		StringBuilder bufferDropTable = new StringBuilder();
		StringBuilder bufferIndexesDrop = new StringBuilder();

		bufferTable.append("CREATE TABLE " + entity.getTableName());
		// define column typeName set

		String separator = "";
		bufferTable.append(" (");

		// for each column, that need to be persisted on table
		for (SQLProperty item : entity.getCollection()) {
			bufferTable.append(separator);
			bufferTable.append(item.columnName);
			
			// every column is a long
			bufferTable.append(" " + SQLTransformer.lookup(TypeName.LONG).getColumnTypeAsString());

			switch (item.columnType) {
			case PRIMARY_KEY:
				bufferTable.append(" PRIMARY KEY AUTOINCREMENT");
				break;
			case UNIQUE:
				bufferTable.append(" UNIQUE");
				break;
			case INDEXED:
				bufferIndexesCreate.append(String.format(" CREATE INDEX idx_%s_%s ON %s(%s);", entity.getTableName(), item.columnName, entity.getTableName(), item.columnName));
				bufferIndexesDrop.append(String.format(" DROP INDEX IF EXISTS idx_%s_%s;", entity.getTableName(), item.columnName));
				break;
			case STANDARD:
				break;
			}

			boolean nullable = item.isNullable();

			// if it is not primary key and it is not nullable, then add not
			// null
			if (!nullable && item.columnType != ColumnType.PRIMARY_KEY) {
				bufferTable.append(" NOT NULL");
			}

			// foreign key
			String foreignClassName = item.foreignClassName;
			if (item.hasForeignKeyClassName()) {
				SQLEntity reference = model.getEntity(foreignClassName);

				if (reference == null) {
					// check if we have a DAO associated into DataSource
					// definition
					boolean found = false;
					for (SQLDaoDefinition daoDefinition : schema.getCollection()) {
						if (daoDefinition.getEntityClassName().equals(foreignClassName)) {
							found = true;
						}
					}

					if (!found) {
						throw new NoDaoElementFound(schema, TypeUtility.className(foreignClassName));
					} else {
						throw new InvalidBeanTypeException(item, foreignClassName);
					}
				}
				
				bufferForeignKey.append(", FOREIGN KEY(" + item.columnName + ") REFERENCES " + reference.getTableName() + "(" + reference.getPrimaryKey().columnName + ")");

				if (item.onDeleteAction != ForeignKeyAction.NO_ACTION) {
					bufferForeignKey.append(" ON DELETE " + item.onDeleteAction.toString().replaceAll("_", " "));
				}

				if (item.onUpdateAction != ForeignKeyAction.NO_ACTION) {
					bufferForeignKey.append(" ON UPDATE " + item.onUpdateAction.toString().replaceAll("_", " "));
				}

				// INSERT as dependency only if reference is another entity.
				// Same entity can not be own dependency.
				if (!entity.getClassName().equals(TypeUtility.typeName(reference.getElement()))) {
					entity.referedEntities.add(reference);
				}

			}

			separator = ", ";
		}

		// add foreign key
		bufferTable.append(bufferForeignKey.toString());
		bufferTable.append(");");

		// add indexes creation one table
		if (bufferIndexesCreate.length() > 0) {
			bufferTable.append(bufferIndexesCreate.toString());
		}

		// add  single column indexes (NOT UNIQUE)
		{
			Pair<String, String> multiIndexes = buldIndexes(entity, false, indexCounter);
			if (!StringUtils.isEmpty(multiIndexes.value0)) {
				bufferTable.append(multiIndexes.value0 + ";");
				bufferIndexesDrop.append(multiIndexes.value1 + ";");
			}
		}

		{
			// create table SQL
			//@formatter:off
 			FieldSpec.Builder fieldSpec = FieldSpec.builder(String.class, "CREATE_TABLE_SQL")
 					.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC);
 			//@formatter:on

			//@formatter:off
 			fieldSpec.addJavadoc("<p>\nDDL to create table $L\n</p>\n",entity.getTableName());
 			fieldSpec.addJavadoc("\n<pre>$L</pre>\n",bufferTable.toString());
 			//@formatter:on

			classBuilder.addField(fieldSpec.initializer("$S", bufferTable.toString()).build());
		}

		// drop table SQL
		// index does not need to be dropped, they are automatically detroyed
		// with tables
		if (bufferIndexesDrop.length() > 0) {
			bufferDropTable.append(bufferIndexesDrop.toString());
		}

		bufferDropTable.append("DROP TABLE IF EXISTS " + entity.getTableName() + ";");

		{
			//@formatter:off
			FieldSpec fieldSpec = FieldSpec.builder(String.class, "DROP_TABLE_SQL")
					.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC)
					.initializer("$S",bufferDropTable.toString())
					.addJavadoc("<p>\nDDL to drop table $L\n</p>\n",entity.getTableName())
		 			.addJavadoc("\n<pre>$L</pre>\n",bufferDropTable.toString())
					.build();
			//@formatter:on
			classBuilder.addField(fieldSpec);
		}

		// define column typeName set
		for (ModelProperty item : entity.getCollection()) {
			item.accept(this);
		}

		ManagedPropertyPersistenceHelper.generateFieldPersistance(context, entity.getCollection(), PersistType.BYTE, true, Modifier.STATIC, Modifier.PUBLIC);

		model.sqlForCreate.add(bufferTable.toString());
		model.sqlForDrop.add(bufferDropTable.toString());

		TypeSpec typeSpec = classBuilder.build();
		JavaWriterHelper.writeJava2File(filer, packageName, typeSpec);

	}

	public static String getTableClassName(String entityName) {
		return  entityName + SUFFIX;
	}

	public static ClassName tableClassName(SQLDaoDefinition dao, SQLEntity entity) {
		String entityName = BindDataSourceSubProcessor.generateEntityQualifiedName(dao, entity);

		// return TypeUtility.className(entity.getName() + SUFFIX);
		return TypeUtility.className(entityName + SUFFIX);

	}

	@Override
	public void visit(SQLiteDatabaseSchema schema, SQLEntity entity) throws Exception {
		int indexCounter = 0;

		// generate the class name that represents the table
		String classTableName = getTableClassName(entity.getSimpleName());

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, classTableName);
		classBuilder = TypeSpec.classBuilder(classTableName).addModifiers(Modifier.PUBLIC).addSuperinterface(SQLiteTable.class);

		BindTypeContext context = new BindTypeContext(classBuilder, TypeUtility.typeName(packageName, classTableName), Modifier.STATIC, Modifier.PRIVATE);

		// javadoc for class
		classBuilder.addJavadoc("<p>");
		classBuilder.addJavadoc("\nEntity <code>$L</code> is associated to table <code>$L</code>\n", entity.getSimpleName(), entity.getTableName());
		classBuilder.addJavadoc("This class represents table associated to entity.\n");
		classBuilder.addJavadoc("</p>\n");
		JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		classBuilder.addJavadoc(" @see $T\n", TypeUtility.className(entity.getName()));

		{
			//@formatter:off
			// table_name
			FieldSpec fieldSpec = FieldSpec.builder(String.class, "TABLE_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
					.initializer("\"$L\"", entity.getTableName())
					.addJavadoc("Costant represents typeName of table $L\n",entity.getTableName())
					.build();
			classBuilder.addField(fieldSpec);
			//@formatter:on
		}

		StringBuilder bufferTable = new StringBuilder();
		StringBuilder bufferForeignKey = new StringBuilder();
		// shared between create table and drop table
		StringBuilder bufferIndexesCreate = new StringBuilder();
		StringBuilder bufferDropTable = new StringBuilder();
		StringBuilder bufferIndexesDrop = new StringBuilder();

		bufferTable.append("CREATE TABLE " + entity.getTableName());
		// define column typeName set

		String separator = "";
		bufferTable.append(" (");

		// for each column, that need to be persisted on table
		for (SQLProperty item : entity.getCollection()) {
			bufferTable.append(separator);
			bufferTable.append(item.columnName);
			bufferTable.append(" " + SQLTransformer.columnTypeAsString(item));

			switch (item.columnType) {
			case PRIMARY_KEY:
				bufferTable.append(" PRIMARY KEY AUTOINCREMENT");
				break;
			case UNIQUE:
				bufferTable.append(" UNIQUE");
				break;
			case INDEXED:
				bufferIndexesCreate.append(String.format(" CREATE INDEX idx_%s_%s ON %s(%s);", entity.getTableName(), item.columnName, entity.getTableName(), item.columnName));
				bufferIndexesDrop.append(String.format(" DROP INDEX IF EXISTS idx_%s_%s;", entity.getTableName(), item.columnName));
				break;
			case STANDARD:
				break;
			}

			boolean nullable = item.isNullable();

			// if it is not primary key and it is not nullable, then add not null
			if (!nullable && item.columnType != ColumnType.PRIMARY_KEY) {
				bufferTable.append(" NOT NULL");
			}

			// foreign key
			String foreignClassName = item.foreignClassName;
			if (item.hasForeignKeyClassName()) {
				SQLEntity reference = model.getEntity(foreignClassName);

				if (reference == null) {
					// check if we have a DAO associated into DataSource definition
					boolean found = false;
					for (SQLDaoDefinition daoDefinition : schema.getCollection()) {
						if (daoDefinition.getEntityClassName().equals(foreignClassName)) {
							found = true;
						}
					}

					if (!found) {
						throw new NoDaoElementFound(schema, TypeUtility.className(foreignClassName));
					} else {
						throw new InvalidBeanTypeException(item, foreignClassName);
					}
				}

				// foreign key can ben used only with column type
				// long/Long
				if (!TypeUtility.isTypeIncludedIn(item.getPropertyType().getTypeName(), Long.class, Long.TYPE)) {
					throw new InvalidForeignKeyTypeException(item);
				}

				bufferForeignKey.append(", FOREIGN KEY(" + item.columnName + ") REFERENCES " + reference.getTableName() + "(" + reference.getPrimaryKey().columnName + ")");

				if (item.onDeleteAction != ForeignKeyAction.NO_ACTION) {
					bufferForeignKey.append(" ON DELETE " + item.onDeleteAction.toString().replaceAll("_", " "));
				}

				if (item.onUpdateAction != ForeignKeyAction.NO_ACTION) {
					bufferForeignKey.append(" ON UPDATE " + item.onUpdateAction.toString().replaceAll("_", " "));
				}

				// INSERT as dependency only if reference is another entity.
				// Same entity can not be own dependency.
				if (!entity.equals(reference)) {
					entity.referedEntities.add(reference);
				}

			}

			separator = ", ";
		}

		// add foreign key
		bufferTable.append(bufferForeignKey.toString());
		bufferTable.append(");");

		// add indexes creation one table
		if (bufferIndexesCreate.length() > 0) {
			bufferTable.append(bufferIndexesCreate.toString());
		}

		// add multicolumn indexes (UNIQUE)
		{
			Pair<String, String> multiIndexes = buldIndexes(entity, true, indexCounter);
			if (!StringUtils.isEmpty(multiIndexes.value0)) {
				bufferTable.append(multiIndexes.value0 + ";");
				bufferIndexesDrop.append(multiIndexes.value1 + ";");
			}
		}

		// add multicolumn indexes (NOT UNIQUE)
		{
			Pair<String, String> multiIndexes = buldIndexes(entity, false, indexCounter);
			if (!StringUtils.isEmpty(multiIndexes.value0)) {
				bufferTable.append(multiIndexes.value0 + ";");
				bufferIndexesDrop.append(multiIndexes.value1 + ";");
			}
		}

		{
			// create table SQL
			//@formatter:off
 			FieldSpec.Builder fieldSpec = FieldSpec.builder(String.class, "CREATE_TABLE_SQL")
 					.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC);
 			//@formatter:on

			//@formatter:off
 			fieldSpec.addJavadoc("<p>\nDDL to create table $L\n</p>\n",entity.getTableName());
 			fieldSpec.addJavadoc("\n<pre>$L</pre>\n",bufferTable.toString());
 			//@formatter:on

			classBuilder.addField(fieldSpec.initializer("$S", bufferTable.toString()).build());
		}

		// drop table SQL
		// index does not need to be dropped, they are automatically detroyed
		// with tables
		if (bufferIndexesDrop.length() > 0) {
			bufferDropTable.append(bufferIndexesDrop.toString());
		}

		bufferDropTable.append("DROP TABLE IF EXISTS " + entity.getTableName() + ";");

		{
			//@formatter:off
			FieldSpec fieldSpec = FieldSpec.builder(String.class, "DROP_TABLE_SQL")
					.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC)
					.initializer("$S",bufferDropTable.toString())
					.addJavadoc("<p>\nDDL to drop table $L\n</p>\n",entity.getTableName())
		 			.addJavadoc("\n<pre>$L</pre>\n",bufferDropTable.toString())
					.build();
			//@formatter:on
			classBuilder.addField(fieldSpec);
		}

		// define column typeName set
		for (ModelProperty item : entity.getCollection()) {
			item.accept(this);
		}

		ManagedPropertyPersistenceHelper.generateFieldPersistance(context, entity.getCollection(), PersistType.BYTE, true, Modifier.STATIC, Modifier.PUBLIC);

		model.sqlForCreate.add(bufferTable.toString());
		model.sqlForDrop.add(bufferDropTable.toString());

		generateColumnsArray(entity);
		
		TypeSpec typeSpec = classBuilder.build();
		JavaWriterHelper.writeJava2File(filer, packageName, typeSpec);

	}

	/**
	 * @param entity
	 */
	private void generateColumnsArray(SQLEntity entity) {
		{
		// generate columns array
		Builder sp = FieldSpec.builder(ArrayTypeName.of(String.class), "COLUMNS", Modifier.STATIC, Modifier.PRIVATE, Modifier.FINAL );
		String s="";
		StringBuilder buffer=new StringBuilder();
		for (SQLProperty property: entity.getCollection()) {
			buffer.append(s+"COLUMN_" + columnNameToUpperCaseConverter.convert(property.getName()));
			s=", ";
		}
		classBuilder.addField(sp.addJavadoc("Columns array\n").initializer("{"+buffer.toString()+"}").build());
		classBuilder.addMethod(MethodSpec.methodBuilder("columns").addModifiers(Modifier.PUBLIC).addJavadoc("Columns array\n").addAnnotation(Override.class).returns(ArrayTypeName.of(String.class)).addStatement("return COLUMNS").build());
		}
	}

	public static Pair<String, String> buldIndexes(final SQLEntity entity, boolean unique, int counter) {
		Pair<String, String> result = new Pair<>();
		result.value0 = "";
		result.value1 = "";

		ModelAnnotation annotationTable = entity.getAnnotation(BindTable.class);
		if (annotationTable == null)
			return result;

		List<String> indexes = null;
		String uniqueString;

		if (unique) {
			uniqueString = "UNIQUE ";
			indexes = annotationTable.getAttributeAsArray(AnnotationAttributeType.UNIQUE_INDEXES);
		} else {
			uniqueString = "";
			indexes = annotationTable.getAttributeAsArray(AnnotationAttributeType.INDEXES);
		}

		if (indexes == null || indexes.size() == 0)
			return result;

		// CREATE INDEX index_name ON tab_name (column1, column2)
		// Matcher matcher = patternIndex.matcher(rawIndexes);

		List<String> listCreateIndex = new ArrayList<>();
		List<String> listDropIndex = new ArrayList<>();
		for (String index : indexes) {
			String createIndex = String.format(" CREATE %sINDEX idx_%s_%s on %s (%s)", uniqueString, entity.getTableName(), counter++, entity.getTableName(), index);
			String dropIndex = String.format(" DROP INDEX IF EXISTS idx_%s_%s", entity.getTableName(), counter);

			final One<Integer> fieldCounter = new One<Integer>(0);
			createIndex = JQLChecker.getInstance().replace(new JQLContext() {

				@Override
				public String getContextDescription() {
					return "While table definition generation for entity " + entity.getName();
				}
			}, createIndex, new JQLReplacerListenerImpl(null) {
											
				@Override
				public String onColumnName(String columnName) {
					fieldCounter.value0++;
					SQLProperty property = entity.findPropertyByName(columnName);

					AssertKripton.assertTrue(property != null, "class '%s' in @%s(indexes) use unknown property '%s'", entity.getName(), BindTable.class.getSimpleName(), columnName);
					return property.columnName;
				}

				@Override
				public String onColumnFullyQualifiedName(String tableName, String columnName) {
					AssertKripton.fail("Inconsistent state");
					return null;
				}
			});

			AssertKripton.assertTrue(fieldCounter.value0 > 0, "class '%s' have @%s(indexes) with no well formed indexes", entity.getName(), BindTable.class.getSimpleName());

			listCreateIndex.add(createIndex);
			listDropIndex.add(dropIndex);
		}

		result.value0 = StringUtils.join(listCreateIndex, ";");
		result.value1 = StringUtils.join(listDropIndex, ";");

		return result;
	}
	
	/**
	 * A generated element can have only a primary key and two FK
	 * @param entity
	 * @param unique
	 * @param counter
	 * @return
	 */
	public static Pair<String, String> buldIndexes(final GeneratedTypeElement entity, boolean unique, int counter) {
		Pair<String, String> result = new Pair<>();
		result.value0 = "";
		result.value1 = "";

		List<String> indexes = null;
		String uniqueString;

		uniqueString = "UNIQUE ";
		indexes = entity.index;
		//  entity.annotationTable.getAttributeAsArray(AnnotationAttributeType.UNIQUE_INDEXES);
		// CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT, student_id INTEGER, seminar_id INTEGER, FOREIGN KEY(student_id) REFERENCES student(id), FOREIGN KEY(seminar_id) REFERENCES seminar(id)); CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc, seminar_id desc);

		if (indexes == null || indexes.size() == 0)
			return result;

		// CREATE INDEX index_name ON tab_name (column1, column2)
		// Matcher matcher = patternIndex.matcher(rawIndexes);

		List<String> listCreateIndex = new ArrayList<>();
		List<String> listDropIndex = new ArrayList<>();
		for (String index : indexes) {
			String createIndex = String.format(" CREATE %sINDEX idx_%s_%s on %s (%s)", uniqueString, entity.getTableName(), counter++, entity.getTableName(), index);
			String dropIndex = String.format(" DROP INDEX IF EXISTS idx_%s_%s", entity.getTableName(), counter);

			listCreateIndex.add(createIndex);
			listDropIndex.add(dropIndex);
		}

		result.value0 = StringUtils.join(listCreateIndex, ";");
		result.value1 = StringUtils.join(listDropIndex, ";");

		return result;
	}

	@Override
	public void visit(SQLProperty kriptonProperty) {
		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(String.class, "COLUMN_" + columnNameToUpperCaseConverter.convert(kriptonProperty.getName()), Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
				.initializer("$S", kriptonProperty.columnName)
				.addJavadoc("Entity's property <code>$L</code> is associated to table column <code>$L</code>. This costant represents column name.\n",
						kriptonProperty.getName(),
						kriptonProperty.columnName)
				.addJavadoc("\n @see $T#$L\n", kriptonProperty.getParentTypeName(), kriptonProperty.getName())
				.build();
		//@formatter:on
		classBuilder.addField(fieldSpec);
	}

}
