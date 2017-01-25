/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sqlite;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.sqlite.NoForeignKey;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
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
		builder = TypeSpec.classBuilder(classTableName).addModifiers(Modifier.PUBLIC);
		
		BindTypeContext context=new BindTypeContext(builder, TypeUtility.typeName(packageName, classTableName), Modifier.STATIC, Modifier.PRIVATE);
		
		// javadoc for class
		builder.addJavadoc("<p>");
		builder.addJavadoc("\nEntity <code>$L</code> is associated to table <code>$L</code>\n", entity.getSimpleName(), entity.getTableName());
		builder.addJavadoc("This class represents table associated to entity.\n");
		builder.addJavadoc("</p>\n");
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc(" @see $T\n", TypeUtility.className(entity.getName()));

		{
			//@formatter:off
			// table_name
			FieldSpec fieldSpec = FieldSpec.builder(String.class, "TABLE_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
					.initializer("\"$L\"", entity.getTableName())
					.addJavadoc("Costant represents name of table $L\n",entity.getTableName())
					.build();
			builder.addField(fieldSpec);
			//@formatter:on
		}

		// shared between create table and drop table
		StringBuilder bufferIndexesCreate=new StringBuilder();
		StringBuilder bufferIndexesDrop=new StringBuilder();
		
		{
			// create table SQL
			//@formatter:off
			// "CREATE TABLE IF NOT EXISTS TutorialsPoint(Username VARCHAR,Password VARCHAR);"
 			FieldSpec.Builder fieldSpec = FieldSpec.builder(String.class, "CREATE_TABLE_SQL")
 					.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC);
 			//@formatter:on

			StringBuilder bufferTable = new StringBuilder();
			StringBuilder bufferForeignKey = new StringBuilder();			
			bufferTable.append("CREATE TABLE " + entity.getTableName());
			// define column name set

			String separator = "";
			// primary key can be column id or that marked as PRIMARY_KEY
			ModelProperty primaryKey = entity.getPrimaryKey();
			ModelAnnotation annotationBindColumn;

			bufferTable.append(" (");
			for (SQLProperty item : entity.getCollection()) {
				bufferTable.append(separator);
				bufferTable.append(item.columnName);
				bufferTable.append(" " + SQLTransformer.columnType(item));

				annotationBindColumn = item.getAnnotation(BindColumn.class);

				if (annotationBindColumn != null) {
					ColumnType columnType = ColumnType.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, item, annotationBindColumn, AnnotationAttributeType.ATTRIBUTE_COLUMN_TYPE));
					switch (columnType) {
					case PRIMARY_KEY:
						bufferTable.append(" PRIMARY KEY AUTOINCREMENT");
						break;
					case UNIQUE:
						bufferTable.append(" UNIQUE");
						break;
					case INDEXED:
						bufferIndexesCreate.append(String.format(" CREATE INDEX %s_%s_idx ON %s(%s);", entity.getTableName(), item.columnName, entity.getTableName(), item.columnName));
						bufferIndexesDrop.append(String.format(" DROP INDEX %s_idx;", item.columnName));
						break;
					case STANDARD:
						break;
					}

					boolean nullable = Boolean.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, item, annotationBindColumn, AnnotationAttributeType.ATTRIBUTE_NULLABLE));
					if (!nullable) {
						bufferTable.append(" NOT NULL");
					}

					// foreign key
					String foreignClassName = annotationBindColumn.getAttributeAsClassName(AnnotationAttributeType.ATTRIBUTE_FOREIGN_KEY);
					if (!foreignClassName.equals(NoForeignKey.class.getName())) {
						SQLEntity reference = model.getEntity(foreignClassName);

						if (reference == null) {
							throw new InvalidBeanTypeException(item, foreignClassName);
						}

						// foreign key can ben used only with column type
						// long/Long
						if (!TypeUtility.isTypeIncludedIn(item.getPropertyType().getName(), Long.class, Long.TYPE)) {
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

			bufferTable.append(bufferForeignKey.toString());
			bufferTable.append(");");			
			
			if (bufferIndexesCreate.length()>0)
			{
				// add indexes creation
				bufferTable.append(bufferIndexesCreate.toString());
			}

			//@formatter:off
			// "CREATE TABLE IF NOT EXISTS TutorialsPoint(Username VARCHAR,Password VARCHAR);"
 			fieldSpec.addJavadoc("<p>\nDDL to create table $L\n</p>\n",entity.getTableName());
 			fieldSpec.addJavadoc("\n<pre>$L</pre>\n",bufferTable.toString());
 			//@formatter:on

			builder.addField(fieldSpec.initializer("$S", bufferTable.toString()).build());
		}

		{
			// drop table SQL
			StringBuilder bufferDropTable=new StringBuilder();
			bufferDropTable.append("DROP TABLE IF EXISTS " + entity.getTableName() + ";");
			
			// it no needed
			//if (bufferIndexesDrop.length()>0)
			//{							
				//bufferDropTable.append(bufferIndexesDrop.toString());
			//}

			//@formatter:off
			FieldSpec fieldSpec = FieldSpec.builder(String.class, "DROP_TABLE_SQL")
					.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC)
					.initializer("$S",bufferDropTable.toString())
					.addJavadoc("<p>\nDDL to drop table $L\n</p>\n",entity.getTableName())
		 			.addJavadoc("\n<pre>$L</pre>\n",bufferDropTable.toString())
					.build();
			//@formatter:on
			builder.addField(fieldSpec);
		}

		// define column name set
		for (ModelProperty item : entity
				.getCollection()) {
			item.accept(this);
		}

		ManagedPropertyPersistenceHelper.generateFieldPersistance(context, entity.getCollection(), PersistType.BYTE, true, Modifier.STATIC, Modifier.PUBLIC);

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

	@Override
	public void visit(SQLProperty kriptonProperty) {
		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(String.class, "COLUMN_" + columnNameToUpperCaseConverter.convert(kriptonProperty.getName()), Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
				.initializer("$S", columnNameToLowerCaseConverter.convert(kriptonProperty.columnName))
				.addJavadoc("Entity's property <code>$L</code> is associated to table column <code>$L</code>. This costant represents column name.\n",
						kriptonProperty.getName(),
						kriptonProperty.columnName)
				.addJavadoc("\n @see $T#$L\n", TypeUtility.className(kriptonProperty.getParent().getName()), kriptonProperty.getName())
				.build();
		//@formatter:on
		builder.addField(fieldSpec);
	}

}
