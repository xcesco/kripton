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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import org.apache.commons.lang3.StringUtils;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.NoForeignKey;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper.PersistType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelElementVisitor;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidBeanTypeException;
import com.abubusoft.kripton.processor.exceptions.InvalidForeignKeyTypeException;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * <p>
 * Generate class ${entity}Table which represents table for entity.
 * </p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class BindTableGenerator extends AbstractBuilder implements ModelElementVisitor<SQLEntity, SQLProperty> {

	public static final String SUFFIX = "Table";

	private Converter<String, String> columnNameToUpperCaseConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
	private Converter<String, String> columnNameToLowerCaseConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

	public BindTableGenerator(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Generate table for entities
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param model
	 * @throws Exception
	 */
	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) throws Exception {
		BindTableGenerator visitor = new BindTableGenerator(elementUtils, filer, model);

		for (SQLEntity item : model.getEntities()) {
			visitor.visit(item);
		}
	}

	public static String tableName(SQLEntity entity) {
		return entity.getSimpleName() + SUFFIX;
	}

	public static ClassName tableClassName(SQLEntity entity) {
		return TypeUtility.className(entity.getName() + SUFFIX);
	}

	@Override
	public void visit(SQLEntity entity) throws Exception {
		entity.buildTableName(elementUtils, model);

		String classTableName = tableName(entity);

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
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

		// shared between create table and drop table
		StringBuilder bufferIndexesCreate = new StringBuilder();
		StringBuilder bufferIndexesDrop = new StringBuilder();

		{
			// create table SQL
			//@formatter:off
 			FieldSpec.Builder fieldSpec = FieldSpec.builder(String.class, "CREATE_TABLE_SQL")
 					.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC);
 			//@formatter:on

			StringBuilder bufferTable = new StringBuilder();
			StringBuilder bufferForeignKey = new StringBuilder();
			bufferTable.append("CREATE TABLE " + entity.getTableName());
			// define column typeName set

			String separator = "";
			// primary key can be column id or that marked as PRIMARY_KEY
			ModelProperty primaryKey = entity.getPrimaryKey();
			ModelAnnotation annotationBindColumn;

			bufferTable.append(" (");
			for (SQLProperty item : entity.getCollection()) {
				bufferTable.append(separator);
				bufferTable.append(item.columnName);
				bufferTable.append(" " + SQLTransformer.columnTypeAsString(item));

				annotationBindColumn = item.getAnnotation(BindColumn.class);

				if (annotationBindColumn != null) {
					ColumnType columnType = ColumnType.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, item, annotationBindColumn, AnnotationAttributeType.COLUMN_TYPE));
					switch (columnType) {
					case PRIMARY_KEY:
						bufferTable.append(" PRIMARY KEY AUTOINCREMENT");
						break;
					case UNIQUE:
						bufferTable.append(" UNIQUE");
						break;
					case INDEXED:
						bufferIndexesCreate.append(String.format(" CREATE INDEX IF NOT EXISTS idx_%s_%s ON %s(%s);", entity.getTableName(), item.columnName, entity.getTableName(), item.columnName));
						bufferIndexesDrop.append(String.format(" DROP INDEX IF EXISTS idx_%s_%s;", entity.getTableName(), item.columnName));
						break;
					case STANDARD:
						break;
					}

					boolean nullable = Boolean.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, item, annotationBindColumn, AnnotationAttributeType.NULLABLE));
					if (!nullable) {
						bufferTable.append(" NOT NULL");
					}

					// foreign key
					String foreignClassName = annotationBindColumn.getAttributeAsClassName(AnnotationAttributeType.FOREIGN_KEY);
					if (!foreignClassName.equals(NoForeignKey.class.getName())) {
						SQLEntity reference = model.getEntity(foreignClassName);

						if (reference == null) {
							throw new InvalidBeanTypeException(item, foreignClassName);
						}

						// foreign key can ben used only with column type
						// long/Long
						if (!TypeUtility.isTypeIncludedIn(item.getPropertyType().getTypeName(), Long.class, Long.TYPE)) {
							throw new InvalidForeignKeyTypeException(item);
						}

						bufferForeignKey.append(", FOREIGN KEY(" + columnNameToLowerCaseConverter.convert(item.getName()) + ") REFERENCES " + reference.buildTableName(elementUtils, model) + "("
								+ columnNameToLowerCaseConverter.convert(reference.getPrimaryKey().columnName) + ")");

						entity.referedEntities.add(reference);
					}

				} else if (primaryKey.equals(item)) {
					bufferTable.append(" PRIMARY KEY AUTOINCREMENT");
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

			// add multicolumn indexes
			Pair<String, String> multiIndexes = buldIndexes(entity);
			if (!StringUtils.isEmpty(multiIndexes.value0)) {
				bufferTable.append(multiIndexes.value0 + ";");
				bufferIndexesDrop.append(multiIndexes.value1 + ";");
			}

			//@formatter:off
 			fieldSpec.addJavadoc("<p>\nDDL to create table $L\n</p>\n",entity.getTableName());
 			fieldSpec.addJavadoc("\n<pre>$L</pre>\n",bufferTable.toString());
 			//@formatter:on

			classBuilder.addField(fieldSpec.initializer("$S", bufferTable.toString()).build());
		}

		{
			// drop table SQL
			StringBuilder bufferDropTable = new StringBuilder();
			bufferDropTable.append("DROP TABLE IF EXISTS " + entity.getTableName() + ";");

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

		TypeSpec typeSpec = classBuilder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

	public static Pair<String, String> buldIndexes(SQLEntity entity) {
		Pair<String, String> result = new Pair<>();
		result.value0 = "";
		result.value1 = "";

		ModelAnnotation annotationTable = entity.getAnnotation(BindTable.class);
		if (annotationTable == null)
			return result;

		List<String> indexes = annotationTable.getAttributeAsArray(AnnotationAttributeType.INDEXES);
		if (indexes == null || indexes.size() == 0)
			return result;

		// CREATE INDEX index_name ON tab_name (column1, column2)
		// Matcher matcher = patternIndex.matcher(rawIndexes);

		int counter = 0;

		List<String> listCreateIndex = new ArrayList<>();
		List<String> listDropIndex = new ArrayList<>();
		for (String index : indexes) {
			String[] fieldList = index.split(",");

			AssertKripton.assertTrue(fieldList != null && fieldList.length > 0, "class '%s' have @%s(indexes) with no well formed indexes", entity.getName(), BindTable.class.getSimpleName());

			List<String> columnList = new ArrayList<>();

			for (String item : fieldList) {
				columnList.add(convertFieldToColumnName(entity, item.trim()));
			}

			String createIndex = String.format(" CREATE INDEX IF NOT EXISTS idx_%s_%s (%s)", entity.getTableName(), counter, StringUtils.join(columnList, ", "));
			String dropIndex = String.format(" DROP INDEX  IF EXISTS idx_%s_%s", entity.getTableName(), counter);
			listCreateIndex.add(createIndex);
			listDropIndex.add(dropIndex);
		}

		result.value0 = StringUtils.join(listCreateIndex, ";");
		result.value1 = StringUtils.join(listDropIndex, ";");

		return result;
	}

	static Pattern wordPattern = Pattern.compile("(\\w+)");

	/**
	 * Look in the string and try to convert first (and unique) field typeName in
	 * its associated column typeName
	 * 
	 * @param entity
	 */
	public static String convertFieldToColumnName(SQLEntity entity, String sql) {
		int found = 0;

		Matcher m = wordPattern.matcher(sql);
		StringBuffer sb = new StringBuffer(sql.length());
		while (m.find()) {
			String text = m.group(1);
			// process
			for (SQLProperty item : entity.getCollection()) {
				if (text.equals(item.getName())) {
					found++;					
					m.appendReplacement(sb, item.columnName);
				}
			}
		}

		AssertKripton.fail(found == 0, "class '%s' in @BindTable(indexes) use invalid field typeName '%s'", entity.getSimpleName(), sql);
		AssertKripton.fail(found > 1, "class '%s' in @BindTable(indexes) use multiple fields '%s'", entity.getSimpleName(), sql);

		m.appendTail(sb);
		return sb.toString();
	}

	@Override
	public void visit(SQLProperty kriptonProperty) {
		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(String.class, "COLUMN_" + columnNameToUpperCaseConverter.convert(kriptonProperty.getName()), Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
				.initializer("$S", columnNameToLowerCaseConverter.convert(kriptonProperty.columnName))
				.addJavadoc("Entity's property <code>$L</code> is associated to table column <code>$L</code>. This costant represents column typeName.\n",
						kriptonProperty.getName(),
						kriptonProperty.columnName)
				.addJavadoc("\n @see $T#$L\n", TypeUtility.className(kriptonProperty.getParent().getName()), kriptonProperty.getName())
				.build();
		//@formatter:on
		classBuilder.addField(fieldSpec);
	}

}
