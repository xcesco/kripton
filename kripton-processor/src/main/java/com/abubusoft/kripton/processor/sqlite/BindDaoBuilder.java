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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.android.sqlite.livedata.KriptonComputableLiveData;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper.PersistType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelElementVisitor;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import com.squareup.javapoet.WildcardTypeName;

import io.reactivex.subjects.PublishSubject;

// TODO: Auto-generated Javadoc
/**
 * Dao generator.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class BindDaoBuilder implements SQLiteModelElementVisitor {

	/** The Constant METHOD_NAME_REGISTRY_EVENT. */
	public static final String METHOD_NAME_REGISTRY_EVENT = "registryEvent";

	/** The Constant METHOD_NAME_INVALIDATE_LIVE_DATA. */
	public static final String METHOD_NAME_INVALIDATE_LIVE_DATA = "invalidateLiveData";

	/** The Constant METHOD_NAME_REGISTRY_LIVE_DATA. */
	public static final String METHOD_NAME_REGISTRY_LIVE_DATA = "registryLiveData";

	/**
	 * Suffix to add to DAO interface to define DAO implementation typeName.
	 */
	public static final String SUFFIX = "Impl";

	/** The element utils. */
	protected Elements elementUtils;
	
	/** The filer. */
	protected Filer filer;
	
	/** The builder. */
	private Builder builder;
	
	/** The current dao definition. */
	private SQLiteDaoDefinition currentDaoDefinition;

	/**
	 * Instantiates a new bind dao builder.
	 *
	 * @param elementUtils the element utils
	 * @param filer the filer
	 */
	public BindDaoBuilder(Elements elementUtils, Filer filer) {
		this.elementUtils = elementUtils;
		this.filer = filer;
	}

	/**
	 * Generate.
	 *
	 * @param elementUtils the element utils
	 * @param filer the filer
	 * @param schema the schema
	 * @throws Exception the exception
	 */
	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		BindDaoBuilder visitor = new BindDaoBuilder(elementUtils, filer);

		for (SQLiteDaoDefinition item : schema.getCollection()) {
			item.accept(visitor);
		}
	}

	/**
	 * Generate second round.
	 *
	 * @param elementUtils the element utils
	 * @param filer the filer
	 * @param schema the schema
	 * @throws Exception the exception
	 */
	public static void generateSecondRound(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		BindDaoBuilder visitor = new BindDaoBuilder(elementUtils, filer);

		for (SQLiteDaoDefinition item : schema.getCollection()) {
			if (item.isGenerated()) {
				item.accept(visitor);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.model.SQLiteModelElementVisitor#visit(com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition)
	 */
	@Override
	public void visit(SQLiteDaoDefinition value) throws Exception {
		currentDaoDefinition = value;

		// check if we need to generate or not
		if (value.getElement().getAnnotation(BindDaoMany2Many.class) != null && value.getElement().getAnnotation(BindGeneratedDao.class) == null) {
			return;
		}

		String classTableName = daoName(value);

		PackageElement pkg = elementUtils.getPackageOf(value.getElement());
		String packageName = pkg.isUnnamed() ? "" : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDao.class, packageName, classTableName);

		builder = TypeSpec.classBuilder(classTableName).superclass(Dao.class).addSuperinterface(typeName(value.getElement())).addModifiers(Modifier.PUBLIC);

		for (TypeName item : value.implementedInterface) {
			builder.addSuperinterface(item);
		}

		BindTypeContext context = new BindTypeContext(builder, TypeUtility.typeName(packageName, classTableName), Modifier.PRIVATE);
		String entityName = BindDataSourceSubProcessor.generateEntityName(value, value.getEntity());

		// javadoc for class
		builder.addJavadoc("<p>");
		builder.addJavadoc("\nDAO implementation for entity <code>$L</code>, based on interface <code>$L</code>\n", entityName, value.getElement().getSimpleName().toString());
		builder.addJavadoc("</p>\n\n");
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc(" @see $T\n", TypeUtility.className(value.getEntityClassName()));
		builder.addJavadoc(" @see $T\n", TypeUtility.className(value.getElement().getQualifiedName().toString()));
		builder.addJavadoc(" @see $T\n", BindTableGenerator.tableClassName(value, value.getEntity()));

		{
			// constructor
			MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC).addParameter(BindDaoFactoryBuilder.generateDaoFactoryClassName(value.getParent()), "daoFactory");
			methodBuilder.addStatement("super(daoFactory.context())");
			builder.addMethod(methodBuilder.build());
		}

		// define column typeName set
		for (SQLiteModelMethod item : value.getCollection()) {
			item.accept(this);
		}

		// generate live data support methods
		if (value.hasLiveData()) {
			// method sendEvent
			{
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(METHOD_NAME_REGISTRY_EVENT).addModifiers(Modifier.PROTECTED).addParameter(Integer.TYPE, "affectedRows");
				methodBuilder.beginControlFlow("if (affectedRows==0)");
				methodBuilder.addStatement("return");
				methodBuilder.endControlFlow();
				
				methodBuilder.beginControlFlow("if (_context.isInSession())");
				methodBuilder.addStatement("_context.registrySQLEvent($T.$L)", BindDataSourceBuilder.generateDataSourceName(value.getParent()), value.daoUidName);
				methodBuilder.nextControlFlow("else");
				methodBuilder.addStatement("invalidateLiveData()");
				methodBuilder.endControlFlow();
				
				builder.addMethod(methodBuilder.build());
			}

			// field liveDatas
			{
				FieldSpec.Builder liveDataBuilder = FieldSpec
						.builder(ParameterizedTypeName.get(ClassName.get(Collection.class),
								ParameterizedTypeName.get(ClassName.get(WeakReference.class),
										ParameterizedTypeName.get(ClassName.get(KriptonComputableLiveData.class), WildcardTypeName.subtypeOf(Object.class)))),
								"liveDatas")
						.addModifiers(Modifier.STATIC)
						.initializer(CodeBlock.builder()
								.add("$T.synchronizedCollection(new $T())", Collections.class, ParameterizedTypeName.get(ClassName.get(HashSet.class), ParameterizedTypeName
										.get(ClassName.get(WeakReference.class), ParameterizedTypeName.get(ClassName.get(KriptonComputableLiveData.class), WildcardTypeName.subtypeOf(Object.class)))))
								.build());
				builder.addField(liveDataBuilder.build());
			}
			
			// registryLiveData
			{
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(METHOD_NAME_REGISTRY_LIVE_DATA).addModifiers(Modifier.PROTECTED)
						.addParameter(ParameterizedTypeName.get(ClassName.get(KriptonComputableLiveData.class), WildcardTypeName.subtypeOf(Object.class)), "value");
				methodBuilder.addStatement("liveDatas.add(new $T(value))",
						ParameterizedTypeName.get(ClassName.get(WeakReference.class), ParameterizedTypeName.get(ClassName.get(KriptonComputableLiveData.class), WildcardTypeName.subtypeOf(Object.class))));
				builder.addMethod(methodBuilder.build());
			}

			// invalidateLiveData
			{
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(METHOD_NAME_INVALIDATE_LIVE_DATA).addModifiers(Modifier.PROTECTED);
				methodBuilder.beginControlFlow("for ($T item: liveDatas)",
						ParameterizedTypeName.get(ClassName.get(WeakReference.class), ParameterizedTypeName.get(ClassName.get(KriptonComputableLiveData.class), WildcardTypeName.subtypeOf(Object.class))));
				methodBuilder.beginControlFlow("if (item.get()!=null)");
				methodBuilder.addStatement("item.get().invalidate()");
				methodBuilder.endControlFlow();
				methodBuilder.endControlFlow();
				builder.addMethod(methodBuilder.build());
			}

		}

		// generate serializer params
		for (Entry<TypeName, String> item : currentDaoDefinition.managedParams.entrySet()) {
			ManagedPropertyPersistenceHelper.generateParamSerializer(context, item.getValue(), item.getKey(), PersistType.BYTE);
			ManagedPropertyPersistenceHelper.generateParamParser(context, item.getValue(), item.getKey(), PersistType.BYTE);
		}

		// generate subject
		if (currentDaoDefinition.getParent().generateRx) {
			ParameterizedTypeName subjectTypeName = ParameterizedTypeName.get(ClassName.get(PublishSubject.class), ClassName.get(SQLiteEvent.class));

			// subject
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("subject").addModifiers(Modifier.PUBLIC);
			methodBuilder.addStatement("return subject").returns(subjectTypeName);
			builder.addMethod(methodBuilder.build());

			// subject instance
			FieldSpec.Builder fieldBuilder = FieldSpec.builder(subjectTypeName, "subject", Modifier.PRIVATE, Modifier.FINAL, Modifier.STATIC).initializer("$T.create()",
					ClassName.get(PublishSubject.class));
			builder.addField(fieldBuilder.build());
		}

		// generate prepared statement cleaner
		{

			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("clearCompiledStatements").addModifiers(Modifier.PUBLIC, Modifier.STATIC).returns(Void.TYPE);
			for (String item : value.preparedStatementNames) {
				methodBuilder.beginControlFlow("if ($L!=null)", item);
				methodBuilder.addStatement("$L.close()", item);
				methodBuilder.addStatement("$L=null", item);
				methodBuilder.endControlFlow();
			}

			builder.addMethod(methodBuilder.build());
		}

		TypeSpec typeSpec = builder.build();

		JavaWriterHelper.writeJava2File(filer, packageName, typeSpec);
	}

	/**
	 * Dao name.
	 *
	 * @param value the value
	 * @return typeName of dao
	 */
	public static String daoName(SQLiteDaoDefinition value) {
		String classTableName = value.getName();
		classTableName = classTableName + SUFFIX;
		return classTableName;
	}

	/**
	 * Dao type name.
	 *
	 * @param value the value
	 * @return the type name
	 */
	public static TypeName daoTypeName(SQLiteDaoDefinition value) {
		return TypeUtility.mergeTypeNameWithSuffix(value.getTypeName(), SUFFIX);
	}

	/**
	 * Dao interface type name.
	 *
	 * @param value the value
	 * @return the type name
	 */
	public static TypeName daoInterfaceTypeName(SQLiteDaoDefinition value) {
		return value.getTypeName();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.model.SQLiteModelElementVisitor#visit(com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod)
	 */
	@Override
	public void visit(SQLiteModelMethod value) throws Exception {
		if (value.getAnnotation(BindSqlInsert.class) != null) {
			SqlInsertBuilder.generate(builder, value);
		} else if (value.getAnnotation(BindSqlUpdate.class) != null) {
			SqlModifyBuilder.generate(builder, value);
		} else if (value.getAnnotation(BindSqlDelete.class) != null) {
			SqlModifyBuilder.generate(builder, value);
		} else if (value.getAnnotation(BindSqlSelect.class) != null) {
			SqlSelectBuilder.generateSelect(builder, value);
		} else {
			// method without supported annotation
			AssertKripton.failWithMethodWithoutSupportedAnnotationException(value);
		}

	}

}
