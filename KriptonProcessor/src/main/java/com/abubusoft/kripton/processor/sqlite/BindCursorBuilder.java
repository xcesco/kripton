/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.processor.core.JavadocUtility;
import com.abubusoft.kripton.processor.core.ModelElementVisitor;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * @author xcesco
 *
 */
public class BindCursorBuilder extends AbstractBuilder implements ModelElementVisitor<SQLEntity, SQLProperty> {

	public static final String PREFIX = "Bind";
	
	public static final String SUFFIX = "Cursor";
	
	private int counter;

	public BindCursorBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	public static void execute(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) throws Exception {
		BindCursorBuilder visitor = new BindCursorBuilder(elementUtils, filer, model);

		for (SQLEntity item : model.getEntities()) {
			visitor.visit(item);
		}

	}

	@Override
	public void visit(SQLEntity entity) throws Exception {
		String classTableName = PREFIX+entity.getSimpleName()+SUFFIX;		

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		ClassName className=TypeUtility.className(packageName, classTableName);

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, classTableName);
		builder = TypeSpec.classBuilder(classTableName).addModifiers(Modifier.PUBLIC);

		// javadoc for class
		builder.addJavadoc("<p>");
		builder.addJavadoc("\nCursor implementation for entity <code>$L</code>\n", entity.getSimpleName());
		builder.addJavadoc("</p>\n");
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc(" @see $T\n", TypeUtility.className(entity.getElement().getQualifiedName().toString()));

		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(Cursor.class, "cursor", Modifier.PROTECTED)
				.addJavadoc("Cursor used to read database\n")
				.build();
		//@formatter:on
		builder.addField(fieldSpec);
		
		// add constructor
		//@formatter:off
		builder.addMethod(MethodSpec.constructorBuilder()
				.addJavadoc("<p>Constructor</p>\n")
				.addJavadoc("@param cursor cursor used to read from database\n")
				.addParameter(Cursor.class, "cursor")
				.addCode("wrap(cursor);\n")
				.build());
		//@formatter:on

		// add wrap method
		//@formatter:off
		MethodSpec.Builder wrapMethodBuilder = MethodSpec.methodBuilder("wrap")
				.addJavadoc("<p>Wrap cursor with this class</p>\n\n")
				.addJavadoc("@param cursor cursor to include\n")
				.addModifiers(Modifier.PUBLIC)
				.addParameter(Cursor.class, "cursor")
				.returns(className)
				.addCode("this.cursor=cursor;\n");
		//@formatter:on
		counter=0;
		wrapMethodBuilder.addCode("\n");
		for (ModelProperty item : entity.getCollection()) {
			wrapMethodBuilder.addCode("index$L=cursor.getColumnIndex($S);\n", counter, SQLUtility.getColumnName(item));
			counter++;
		}
		wrapMethodBuilder.addCode("\n");
		wrapMethodBuilder.addCode("return this;\n");
		
		builder.addMethod(wrapMethodBuilder.build());
		
		// add execute method		
		builder.addMethod(generateExecuteMethod(packageName, entity).build());
		
		// add execute listener method
		builder.addMethod(generateExecuteListener(packageName, entity).build());

		// add create
		//@formatter:off
		builder.addMethod(
				MethodSpec.methodBuilder("create")
				.addModifiers(Modifier.STATIC, Modifier.PUBLIC)
				.addParameter(Cursor.class, "cursor")
				.returns(className(packageName, classTableName))
				.addJavadoc("<p>Create a binded cursor starting from a cursor</p>\n\n")
				.addJavadoc("@param cursor to wrap\n")
				.addCode("return new "+classTableName+"(cursor);\n")				
				.build());
		//@formatter:on

		// define column name set
		counter=0;
		for (ModelProperty item : entity.getCollection()) {
			item.accept(this);
		}
		
		TypeSpec typeSpec = builder.build();			
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);

	}



	private MethodSpec.Builder generateExecuteMethod(String packageName, SQLEntity entity) {		
		ParameterizedTypeName parameterizedReturnTypeName = ParameterizedTypeName.get(className("java.util","LinkedList"), className(packageName, entity.getSimpleName()));
		
		//@formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("execute")
				.addJavadoc("<p>Execute the cursor and read all rows from database.</p>\n\n")
				.addJavadoc("@return list of beans\n")
				.addModifiers(Modifier.PUBLIC)
				.returns(parameterizedReturnTypeName);
		//@formatter:on

		TypeName entityClass= typeName(entity.getElement());
		
		methodBuilder.addCode("\n");
		methodBuilder.addCode("$T resultList=new $T();\n",parameterizedReturnTypeName, parameterizedReturnTypeName);
		methodBuilder.addCode("$T resultBean=new $T();\n",entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		methodBuilder.beginControlFlow("do\n");			
		methodBuilder.addCode("resultBean=new $T();\n\n",entityClass);
		
		// generate mapping
		int i=0;
		for (ModelProperty item : entity.getCollection()) {			
			methodBuilder.addCode("if (index$L>=0 && !cursor.isNull(index$L)) { ",i,i);
			Transformer.cursor2Java(methodBuilder, entityClass, item, "resultBean", "cursor","index"+i+"");		
			methodBuilder.addCode(";");
			methodBuilder.addCode("}\n");
			
			i++;
		}
		methodBuilder.addCode("\n");
		
		methodBuilder.addCode("resultList.add(resultBean);\n");			
		methodBuilder.endControlFlow("while (cursor.moveToNext())");						
		
		methodBuilder.endControlFlow();
		methodBuilder.addCode("cursor.close();\n");
		
		methodBuilder.addCode("\n");
		methodBuilder.addCode("return resultList;\n");
		
		return methodBuilder;
	}
	
	private MethodSpec.Builder generateExecuteListener(String packageName, SQLEntity entity) {		
		String interfaceName="On" + entity.getSimpleName() + "Listener";
		
		//@formatter:off
		Builder listenerInterface = TypeSpec.interfaceBuilder(interfaceName)
				.addModifiers(Modifier.PUBLIC)
				.addMethod(MethodSpec.methodBuilder("onRow")
						.addJavadoc("Method executed for each row extracted from database\n\n")
						.addJavadoc("@param bean loaded from database. Only selected columns/fields are valorized\n")
						.addJavadoc("@param rowPosition position of row\n")
						.addJavadoc("@param rowCount total number of rows\n")
						.addParameter(ParameterSpec.builder(typeName(entity.getElement()), "bean").build())
						.addParameter(ParameterSpec.builder(Integer.TYPE, "rowPosition").build())
						.addParameter(ParameterSpec.builder(Integer.TYPE, "rowCount").build())
				.returns(Void.TYPE).addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).build());
		//@formatter:on
		
		ClassName interfaceType=TypeUtility.className("", interfaceName);
		
		
		// javadoc for interface
		listenerInterface.addJavadoc("<p>Listener for row read from database.</p>\n");			
		TypeSpec listenerClass = listenerInterface.build();
		
		builder.addType(listenerClass);
		
		//@formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("executeListener")
				.addJavadoc("Method executed for each row extracted from database. For each row specified listener will be invoked.\n\n")
				.addJavadoc("@param listener listener to invoke for each row\n")				
				.addModifiers(Modifier.PUBLIC)
				.addParameter(ParameterSpec.builder(interfaceType, "listener").build())
				.returns(TypeName.VOID);
		//@formatter:on

		TypeName entityClass= typeName(entity.getElement());
		
		methodBuilder.addCode("$T resultBean=new $T();\n",entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		methodBuilder.beginControlFlow("do\n");					
		
		// reset mapping
		{
			int i=0;
			for (ModelProperty item : entity.getCollection()) {			
				methodBuilder.addCode("if (index$L>=0) { ",i);
				Transformer.resetBean(methodBuilder, entityClass, "resultBean", item,  "cursor","index"+i+"");
				methodBuilder.addCode(";");
				methodBuilder.addCode("}\n");
				
				i++;
			}
		}
		methodBuilder.addCode("\n");
		
		// generate mapping
		{
			int i=0;
			for (ModelProperty item : entity.getCollection()) {			
				methodBuilder.addCode("if (index$L>=0 && !cursor.isNull(index$L)) { ",i,i);
				Transformer.cursor2Java(methodBuilder, entityClass, item, "resultBean", "cursor","index"+i+"");
				methodBuilder.addCode(";");
				methodBuilder.addCode("}\n");
				
				i++;
			}
		}

		// send to listener
		methodBuilder.addCode("\n");
		methodBuilder.addCode("listener.onRow(resultBean, cursor.getPosition(),cursor.getCount());\n");
				
		methodBuilder.endControlFlow("while (cursor.moveToNext())");						
		
		methodBuilder.endControlFlow();
		methodBuilder.addCode("cursor.close();\n");
		
		return methodBuilder;
	}

	@Override
	public void visit(SQLProperty property) throws Exception {
		// add property index
		builder.addField(FieldSpec.builder(Integer.TYPE, "index"+(counter++), Modifier.PROTECTED).addJavadoc("Index for column $S\n", property.getName()).build());

	}

	

}
