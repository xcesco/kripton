package com.abubusoft.kripton.processor.sqlite;

import java.util.Date;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.BindDataSourceProcessor;
import com.abubusoft.kripton.processor.Version;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelElementVisitor;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * <p>Generate class ${entity}Table which represents table for entity.</p>
 * 
 * @author xcesco
 *
 */
public class TableGenerator extends AbstractBuilder implements ModelElementVisitor<SQLEntity, SQLProperty> {

	public static final String SUFFIX = "Table";

	private Converter<String, String> columnNameConverter;

	public TableGenerator(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
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
		TableGenerator visitor = new TableGenerator(elementUtils, filer, model);

		for (SQLEntity item : model.getEntities()) {
			visitor.visit(item);
		}
	}

	@Override
	public void visit(SQLEntity kriptonClass) throws Exception {
		SQLEntity entity=kriptonClass;
		entity.buildTableName(elementUtils, model);
				
		String classTableName =  entity.getSimpleName() + SUFFIX;	

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, classTableName);
		builder = TypeSpec.classBuilder(classTableName).addModifiers(Modifier.PUBLIC);
		// javadoc for class
		builder.addJavadoc("<p>");
		builder.addJavadoc("\nEntity <code>$L</code> is associated to table <code>$L</code>\n",entity.getSimpleName(), entity.getTableName());
		builder.addJavadoc("This class represents table associated to entity.\n");
		builder.addJavadoc("</p>\n");
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc(" @see $T\n", TypeUtility.className(entity.getName()));

		columnNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

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
		
		{
			// create table SQL
			//@formatter:off
			// "CREATE TABLE IF NOT EXISTS TutorialsPoint(Username VARCHAR,Password VARCHAR);"
 			FieldSpec.Builder fieldSpec = FieldSpec.builder(String.class, "CREATE_TABLE_SQL")
 					.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC);
 			//@formatter:on
 			
			StringBuilder buffer=new StringBuilder();
			buffer.append("CREATE TABLE "+entity.getTableName());
			// define column name set
			
			String separator="";
			// primary key can be column id or that marked as PRIMARY_KEY
			ModelProperty primaryKey = entity.getPrimaryKey();
			ModelAnnotation annotation;
			
			buffer.append(" (");
			for (ModelProperty item : entity.getCollection()) {
				buffer.append(separator);
				buffer.append(model.classNameConverter.convert(item.getName()));
				buffer.append(" "+Transformer.columnType(item));
				
				annotation=item.getAnnotation(BindColumn.class);
				
				if (annotation!=null)
				{
					ColumnType columnType=ColumnType.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, item, annotation, AnnotationAttributeType.ATTRIBUTE_VALUE));
					switch(columnType)
					{
					case PRIMARY_KEY:
						buffer.append(" PRIMARY KEY AUTOINCREMENT");
						break;
					case UNIQUE:
						buffer.append(" UNIQUE");
						break;
					case STANDARD:
						break;
					case FOREIGN_KEY:
						break;
					}
					
					boolean nullable=Boolean.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, item, annotation, AnnotationAttributeType.ATTRIBUTE_NULLABLE));				
					if (!nullable)
					{
						buffer.append(" NOT NULL");
					}
				} else if (primaryKey.equals(item)) {
					buffer.append(" PRIMARY KEY AUTOINCREMENT");
				}
				
				separator=", ";
			}
			buffer.append(")");
			buffer.append(";");
			
			//@formatter:off
			// "CREATE TABLE IF NOT EXISTS TutorialsPoint(Username VARCHAR,Password VARCHAR);"
 			fieldSpec.addJavadoc("<p>\nDDL to create table $L\n</p>\n",entity.getTableName());
 			fieldSpec.addJavadoc("\n<pre>$L</pre>\n",buffer.toString());
 			//@formatter:on
			
			builder.addField(fieldSpec.initializer("$S",buffer.toString()).build());						
		}
		
		{			
			// drop table SQL
			// "CREATE TABLE IF NOT EXISTS TutorialsPoint(Username VARCHAR,Password VARCHAR);"
			String sql="DROP TABLE IF EXISTS "+entity.getTableName()+";";
			
			//@formatter:off
			FieldSpec fieldSpec = FieldSpec.builder(String.class, "DROP_TABLE_SQL")
					.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC)
					.initializer("$S",sql)
					.addJavadoc("<p>\nDDL to drop table $L\n</p>\n",entity.getTableName())
		 			.addJavadoc("\n<pre>$L</pre>\n",sql)
					.build();
			//@formatter:on
			builder.addField(fieldSpec);
		}

		// define column name set
		for (ModelProperty item : kriptonClass.getCollection()) {
			item.accept(this);
		}

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

	@Override
	public void visit(SQLProperty kriptonProperty) {
		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(String.class, "COLUMN_" + columnNameConverter.convert(kriptonProperty.getName()), Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
				.initializer("\"$L\"", model.columnNameConverter.convert(kriptonProperty.getName()))
				.addJavadoc("Entity's property <code>$L</code> is associated to table column <code>$L</code>. This costant represents column name.\n",
						kriptonProperty.getName(),
						model.columnNameConverter.convert(kriptonProperty.getName()))
				.addJavadoc("\n @see $T#$L\n", TypeUtility.className(kriptonProperty.getParent().getName()), kriptonProperty.getName())
				.build();
		//@formatter:on
		builder.addField(fieldSpec);
	}


}
