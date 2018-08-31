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
/**
 * 
 */
package com.abubusoft.kripton.processor.sharedprefs;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.KriptonLiveDataManager;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ImmutableUtility;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper.PersistType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelEntity;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsEntity;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;
import com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransformer;
import com.abubusoft.kripton.processor.sharedprefs.transform.ReadType;
import com.abubusoft.kripton.processor.sharedprefs.transform.SetPrefsTransformation;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * The Class BindSharedPreferencesBuilder.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class BindSharedPreferencesBuilder {

	/** The Constant PREFIX. */
	protected static final String PREFIX = "Bind";

	/** The Constant SUFFIX_SHARED_PREFERENCE. */
	protected static final String SUFFIX_SHARED_PREFERENCE = "SharedPreferences";

	/** The Constant SUFFIX_PREFERENCE. */
	protected static final String SUFFIX_PREFERENCE = "Preferences";

	/** The builder. */
	protected static Builder builder;

	public static String getBuildPreferenceName(ModelEntity<?> entity) {
		String beanClassName = entity.getElement().getSimpleName().toString();
		String suffix;

		if (beanClassName.endsWith(SUFFIX_SHARED_PREFERENCE)) {
			suffix = "";
		} else if (beanClassName.endsWith(SUFFIX_PREFERENCE)) {
			suffix = "";
		} else {
			suffix = SUFFIX_SHARED_PREFERENCE;
		}

		String className = PREFIX + beanClassName + suffix;

		return className;
	}

	/**
	 * Generate shared preference manager.
	 *
	 * @param elementUtils
	 *            the element utils
	 * @param filer
	 *            the filer
	 * @param entity
	 *            the entity
	 * @return typeName of generated class
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String generate(Elements elementUtils, Filer filer, PrefsEntity entity) throws IOException {
		com.abubusoft.kripton.common.Converter<String, String> converter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		String beanClassName = entity.getElement().getSimpleName().toString();
		String className = getBuildPreferenceName(entity);
		ModelAnnotation annotation = entity.getAnnotation(BindSharedPreferences.class);
		String sharedPreferenceName = annotation.getAttribute(AnnotationAttributeType.VALUE);
		boolean generateRx = entity.hasRxProperties();
		boolean generateLiveData = entity.hasLiveDataProperties();

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? "" : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindSharedPreferences.class, packageName, className);
		// @formatter:off
		builder = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC)
				.superclass(AbstractSharedPreference.class);

		BindTypeContext context = new BindTypeContext(builder, TypeUtility.typeName(entity.getElement()),
				Modifier.PRIVATE);

		// @formatter:on	
		builder.addJavadoc("This class is the shared preference binder defined for $T\n\n", entity.getElement());
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc("@see $T\n", entity.getElement());

		if (StringUtils.hasText(sharedPreferenceName)) {
			builder.addField(FieldSpec.builder(String.class, "SHARED_PREFERENCE_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
					.initializer("$S", converter.convert(entity.getSimpleName().toString())).addJavadoc("shared preferences typeName for $T\n", entity.getElement()).build());
		}

		builder.addField(FieldSpec.builder(className(beanClassName), "defaultBean", Modifier.PRIVATE, Modifier.FINAL).addJavadoc("working instance of bean\n").build());

		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("edit").addJavadoc("create an editor to modify shared preferences\n").returns(typeName("BindEditor"))
					.addModifiers(Modifier.PUBLIC);
			methodBuilder.addStatement("return new $T()", typeName("BindEditor"));
			builder.addMethod(methodBuilder.build());
		}

		generateEditor(entity);

		generateCreate(sharedPreferenceName, beanClassName, generateRx, generateLiveData);

		generateConstructor(entity, sharedPreferenceName, beanClassName);

		generateRefresh(sharedPreferenceName, className);

		generateRxSupport(sharedPreferenceName, beanClassName, generateRx, entity);

		generateLiveDataSupport(sharedPreferenceName, beanClassName, generateLiveData, entity);

		generateResetMethod(entity);

		generateReadMethod(entity);

		generateWriteMethod(entity);

		generateSingleReadMethod(entity);

		// generate all needed writer and reader
		List<PrefsProperty> fields = entity.getCollection();
		List<PrefsProperty> filteredFields = new ArrayList<>();

		// we need to avoid generation of persists values
		for (PrefsProperty item : fields) {
			if (SetPrefsTransformation.isStringSet(item)) {
				continue;
			} else {
				filteredFields.add(item);
			}
		}

		// avoid to consider StringSet
		ManagedPropertyPersistenceHelper.generateFieldPersistance(context, filteredFields, PersistType.STRING, false, Modifier.PROTECTED);

		generateInstance(className);

		TypeSpec typeSpec = builder.build();
		JavaWriterHelper.writeJava2File(filer, packageName, typeSpec);

		return className;
	}

	private static void generateCreate(String sharedPreferenceName, String beanClassName, boolean generateRx, boolean generateLiveData) {
		MethodSpec.Builder method = MethodSpec.methodBuilder("createPrefs").addModifiers(Modifier.PRIVATE).addJavadoc("create prefs\n");
		if (StringUtils.hasText(sharedPreferenceName)) {
			method.addCode("// using typeName attribute of annotation @BindSharedPreferences as typeName\n");
			method.addStatement("prefs=$T.getContext().getSharedPreferences(SHARED_PREFERENCE_NAME, $T.MODE_PRIVATE)", KriptonLibrary.class, Context.class);
		} else {
			method.addCode("// no typeName specified, using default shared preferences\n");
			method.addStatement("prefs=$T.getDefaultSharedPreferences($T.getContext())", PreferenceManager.class, KriptonLibrary.class);
		}

		if (generateRx) {
			method.addStatement("prefs.registerOnSharedPreferenceChangeListener(rxListener)");
		}

		if (generateLiveData) {
			method.addStatement("prefs.registerOnSharedPreferenceChangeListener(liveDataListener)");
		}
		builder.addMethod(method.build());
	}

	private static void generateLiveDataSupport(String sharedPreferenceName, String beanClassName, boolean generateLiveData, PrefsEntity entity) {
		if (!generateLiveData)
			return;

		// generate livedata collections
		FieldSpec fs = FieldSpec
				.builder(ParameterizedTypeName.get(ClassName.get(List.class),
						ParameterizedTypeName.get(ClassName.get(Pair.class), ClassName.get(String.class),
								ParameterizedTypeName.get(ClassName.get(WeakReference.class), ClassName.get(LiveDataHandler.class)))),
						"liveDatas", Modifier.PRIVATE)
				.initializer(CodeBlock.of("new $T<$T<String, WeakReference<$T>>>()", CopyOnWriteArrayList.class, Pair.class, LiveDataHandler.class))
				.addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "\"rawtypes\"").build()).build();
		builder.addField(fs);
		{ // registryLiveData
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("registryLiveData").addModifiers(Modifier.PROTECTED).addParameter(String.class, "key")
					.addParameter(ClassName.get(LiveDataHandler.class), "value").addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "\"rawtypes\"").build())
					.addStatement("liveDatas.add(new Pair<String , WeakReference<$T>>(key, new WeakReference<$T>(value)))", LiveDataHandler.class, LiveDataHandler.class);
			builder.addMethod(methodBuilder.build());
		}

		{ // update LiveData
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("updateLiveData").addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "\"rawtypes\"").build())
					.addModifiers(Modifier.PROTECTED).addParameter(String.class, "key").addParameter(Object.class, "value")

					.beginControlFlow("for (Pair<String, WeakReference<$T>> item : liveDatas)", LiveDataHandler.class).beginControlFlow("if (item.value0.equals(key) && item.value1.get() != null)")
					.addStatement("item.value1.get().invalidate()").endControlFlow().endControlFlow();

			builder.addMethod(methodBuilder.build());
		}

		Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onSharedPreferenceChanged").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC)
				.addParameter(SharedPreferences.class, "sharedPreferences", Modifier.FINAL).addParameter(String.class, "key", Modifier.FINAL);

		{
			// inner method run
			MethodSpec.Builder runBuilder = MethodSpec.methodBuilder("run").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class);

			// ----
			runBuilder.beginControlFlow("switch (key)");
			PrefsTransform transform;
			TypeName typeName;
			for (PrefsProperty item : entity.getCollection()) {
				if (!item.isGenerateLiveData())
					continue;

				if (item.hasTypeAdapter()) {
					transform = PrefsTransformer.lookup(item.typeAdapter.getDataTypeTypename());
				} else {
					transform = PrefsTransformer.lookup(item);
				}

				typeName = item.getPropertyType().getTypeName();
				if (TypeUtility.isTypePrimitive(typeName)) {
					typeName = typeName.box();
				}

				generateMethodAsLiveData(converter, typeName, item);

				runBuilder.addComment("$L - $L", item.getPreferenceKey(), item.getName());
				runBuilder.addCode("case $S: {\n", item.getPreferenceKey());

				transform.generateReadProperty(runBuilder, "sharedPreferences", typeName(item.getElement().asType()), "defaultBean", item, false, ReadType.VALUE);
				runBuilder.addCode("\n");

				runBuilder.addStatement("updateLiveData($S, _value)", item.getPreferenceKey());

				runBuilder.addStatement("return");
				runBuilder.addCode("}\n");
			}
			runBuilder.addStatement("default: return");
			runBuilder.endControlFlow();

			// ----

			{ // run builder
				TypeSpec innerBuilder = TypeSpec.anonymousClassBuilder("").addSuperinterface(Runnable.class).addMethod(runBuilder.build()).build();

				methodBuilder.addStatement("$T.getExecutorService().execute($L)", KriptonLibrary.class, innerBuilder);
			}
		}

		TypeSpec innerBuilder = TypeSpec.anonymousClassBuilder("").addSuperinterface(OnSharedPreferenceChangeListener.class).addMethod(methodBuilder.build()).build();

		FieldSpec.Builder f = FieldSpec.builder(ClassName.get(OnSharedPreferenceChangeListener.class), "liveDataListener", Modifier.PRIVATE)
				.addJavadoc("Listener used to propagate shared prefs changes through RX\n").initializer("$L", innerBuilder);

		builder.addField(f.build());
	}

	private static void generateMethodAsLiveData(Converter<String, String> converter, TypeName typeName, PrefsProperty property) {

		ParameterizedTypeName liveDataType = ParameterizedTypeName.get(ClassName.get(KriptonLiveDataManager.getInstance().getLiveDataHandlerClazz()), typeName);
		String className = getBuildPreferenceName(property.getParent());

		// generate compute
		MethodSpec.Builder computeBuilder = MethodSpec.methodBuilder("compute").addModifiers(Modifier.PROTECTED).returns(typeName).addAnnotation(Override.class);
		computeBuilder.addStatement("$L.this.refresh()", className);
		computeBuilder.addStatement("return $L.this.get$L()", className, converter.convert(property.getName()));

		// generate builder
		TypeSpec liveDataBuilder = TypeSpec.anonymousClassBuilder("").addSuperinterface(liveDataType).addMethod(computeBuilder.build()).build();

		MethodSpec ms = MethodSpec.methodBuilder("get" + converter.convert(property.getName()) + "AsLiveData").addModifiers(Modifier.PUBLIC)
				.returns(ParameterizedTypeName.get(ClassName.get(KriptonLiveDataManager.getInstance().getMutableLiveDataClazz()), typeName))
				.addJavadoc("Obtains an LiveData to <code>$L</code> property\n\n", property.getName()).addJavadoc("@return\nan LiveData to <code>$L</code> property\n", property.getName())
				.addStatement("$T liveData=$L", liveDataType, liveDataBuilder).addStatement("registryLiveData($S, liveData)", property.getPreferenceKey()).addStatement("return liveData.getLiveData()")
				.build();

		builder.addMethod(ms);
	}

	private static void generateRxSupport(String sharedPreferenceName, String beanClassName, boolean generateRx, PrefsEntity entity) {
		if (!generateRx)
			return;
		Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

		com.squareup.javapoet.MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onSharedPreferenceChanged").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC)
				.addParameter(SharedPreferences.class, "sharedPreferences").addParameter(String.class, "key");

		methodBuilder.beginControlFlow("switch (key)");
		String subjectFieldName;
		PrefsTransform transform;
		TypeName typeName;
		for (PrefsProperty item : entity.getCollection()) {
			if (!item.isGenerateRx())
				continue;

			if (item.hasTypeAdapter()) {
				transform = PrefsTransformer.lookup(item.typeAdapter.getDataTypeTypename());
			} else {
				transform = PrefsTransformer.lookup(item);
			}

			typeName = item.getPropertyType().getTypeName();
			if (TypeUtility.isTypePrimitive(typeName)) {
				typeName = typeName.box();
			}

			// add subject fields
			subjectFieldName = item.getName() + "Subject";
			FieldSpec fs = FieldSpec.builder(ParameterizedTypeName.get(ClassName.get(Subject.class), typeName), subjectFieldName, Modifier.PRIVATE)
					.addJavadoc("subject for field $L - shared pref $L\n", item.getName(), item.getPreferenceKey()).initializer(CodeBlock.of("$T.create()", BehaviorSubject.class)).build();

			MethodSpec ms = MethodSpec.methodBuilder("get" + converter.convert(item.getName()) + "AsObservable").addModifiers(Modifier.PUBLIC)
					.returns(ParameterizedTypeName.get(ClassName.get(Subject.class), typeName)).addJavadoc("Obtains an observable to <code>$L</code> property\n\n", item.getName())
					.addJavadoc("@return\nan observable to <code>$L</code> property\n", item.getName()).addStatement("return $L", subjectFieldName).build();

			methodBuilder.addComment("$L - $L", item.getPreferenceKey(), item.getName());
			methodBuilder.addCode("case $S: {\n", item.getPreferenceKey());

			transform.generateReadProperty(methodBuilder, "sharedPreferences", typeName(item.getElement().asType()), "defaultBean", item, false, ReadType.VALUE);
			methodBuilder.addCode("\n");

			if (!TypeUtility.isTypePrimitive(item.getPropertyType().getTypeName())) {
				methodBuilder.beginControlFlow("if (_value!=null)");
			}
			methodBuilder.addStatement("$L.onNext(_value)", subjectFieldName);
			if (!TypeUtility.isTypePrimitive(item.getPropertyType().getTypeName())) {
				methodBuilder.endControlFlow();
			}
			methodBuilder.addStatement("return");
			methodBuilder.addCode("}\n", subjectFieldName);

			builder.addField(fs);
			builder.addMethod(ms);

		}

		methodBuilder.addStatement("default: return");
		methodBuilder.endControlFlow();

		TypeSpec innerBuilder = TypeSpec.anonymousClassBuilder("").addSuperinterface(OnSharedPreferenceChangeListener.class).addMethod(methodBuilder.build()).build();

		FieldSpec.Builder f = FieldSpec.builder(ClassName.get(OnSharedPreferenceChangeListener.class), "rxListener", Modifier.PRIVATE)
				.addJavadoc("Listener used to propagate shared prefs changes through RX\n").initializer("$L", innerBuilder);

		builder.addField(f.build());
	}

	/**
	 * create editor.
	 *
	 * @param entity
	 *            the entity
	 */
	private static void generateEditor(PrefsEntity entity) {
		com.abubusoft.kripton.common.Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		Builder innerClassBuilder = TypeSpec.classBuilder("BindEditor").addModifiers(Modifier.PUBLIC).addJavadoc("editor class for shared preferences\n").superclass(typeName("AbstractEditor"));
		innerClassBuilder.addMethod(MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE).build());

		PrefsTransform transform;
		// write method
		for (PrefsProperty item : entity.getCollection()) {
			{ // put
				MethodSpec.Builder builder = MethodSpec.methodBuilder("put" + converter.convert(item.getName())).addModifiers(Modifier.PUBLIC).addParameter(typeName(item.getElement()), "value")
						.addJavadoc("modifier for property $L\n", item.getName()).returns(typeName("BindEditor"));

				TypeName type;
				if (item.hasTypeAdapter()) {
					type = typeName(item.typeAdapter.dataType);
				} else {
					type = TypeUtility.typeName(item.getElement());
				}

				transform = PrefsTransformer.lookup(type);
				transform.generateWriteProperty(builder, "editor", null, "value", item);
				builder.addCode("\n");

				builder.addStatement("return this");
				innerClassBuilder.addMethod(builder.build());
			}

			{ // remove
				MethodSpec.Builder builder = MethodSpec.methodBuilder("remove" + converter.convert(item.getName())).addModifiers(Modifier.PUBLIC).addJavadoc("remove property $L\n", item.getName())
						.returns(typeName("BindEditor"));

				builder.addStatement("editor.remove($S)", item.getPreferenceKey());

				builder.addStatement("return this");
				innerClassBuilder.addMethod(builder.build());
			}
		}

		{ // clear
			MethodSpec.Builder builder = MethodSpec.methodBuilder("clear").addModifiers(Modifier.PUBLIC).addJavadoc("clear all properties\n").returns(typeName("BindEditor"));

			builder.addStatement("editor.clear()");
			builder.addStatement("return this");
			innerClassBuilder.addMethod(builder.build());
		}

		builder.addType(innerClassBuilder.build());
	}

	/**
	 * Generate instance of shared preferences.
	 *
	 * @param className
	 *            the class name
	 */
	private static void generateInstance(String className) {
		builder.addField(FieldSpec.builder(className(className), "instance", Modifier.PRIVATE, Modifier.STATIC).addJavadoc("instance of shared preferences\n").build());
		// instance
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("getInstance").addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.SYNCHRONIZED)
				.addJavadoc("get instance of shared preferences\n").returns(className(className));

		methodBuilder.beginControlFlow("if (instance==null)");
		methodBuilder.addStatement("instance=new $L()", className(className));
		methodBuilder.addComment("read and write instance to sync with default values");
		methodBuilder.addStatement("instance.write(instance.read())");
		methodBuilder.endControlFlow();
		methodBuilder.addCode("return instance;\n");

		builder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate constructor.
	 * 
	 * @param entity
	 *
	 * @param sharedPreferenceName
	 *            the shared preference name
	 * @param beanClassName
	 *            the bean class name
	 */
	private static void generateConstructor(PrefsEntity entity, String sharedPreferenceName, String beanClassName) {
		MethodSpec.Builder method = MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE).addJavadoc("constructor\n");

		method.addStatement("createPrefs()");

		if (entity.isImmutablePojo()) {
			ImmutableUtility.generateImmutableVariableInit(entity, method);
			ImmutableUtility.generateImmutableEntityCreation(entity, method, "defaultBean", false);
		} else {
			method.addStatement("defaultBean=new $T()", className(beanClassName));
		}

		builder.addMethod(method.build());
	}

	/**
	 * Generate refresh.
	 *
	 * @param sharedPreferenceName
	 *            the shared preference name
	 * @param className
	 *            the class name
	 */
	private static void generateRefresh(String sharedPreferenceName, String className) {
		MethodSpec.Builder method = MethodSpec.methodBuilder("refresh").addModifiers(Modifier.PUBLIC).addJavadoc("force to refresh values\n").returns(className(className));

		method.addStatement("createPrefs()");
		method.addStatement("return this");
		builder.addMethod(method.build());
	}

	/**
	 * Generate reset method.
	 *
	 * @param entity
	 *            the entity
	 */
	private static void generateResetMethod(PrefsEntity entity) {
		// write method
		MethodSpec.Builder method = MethodSpec.methodBuilder("reset").addModifiers(Modifier.PUBLIC).addJavadoc("reset shared preferences\n").returns(Void.TYPE);

		if (entity.isImmutablePojo()) {
			ImmutableUtility.generateImmutableVariableInit(entity, method);
			ImmutableUtility.generateImmutableEntityCreation(entity, method, "bean", true);
		} else {
			method.addStatement("$T bean=new $T()", entity.getElement(), entity.getElement());
		}

		method.addStatement("write(bean)");
		builder.addMethod(method.build());
	}

	/**
	 * Generate write method.
	 *
	 * @param entity
	 *            the entity
	 */
	private static void generateWriteMethod(PrefsEntity entity) {
		// write method
		MethodSpec.Builder method = MethodSpec.methodBuilder("write").addJavadoc("write bean entirely\n\n").addJavadoc("@param bean bean to entirely write\n").addModifiers(Modifier.PUBLIC)
				.addParameter(typeName(entity.getName()), "bean").returns(Void.TYPE);
		method.addStatement("$T editor=prefs.edit()", SharedPreferences.Editor.class);

		PrefsTransform transform;
		for (PrefsProperty item : entity.getCollection()) {
			if (item.hasTypeAdapter()) {
				transform = PrefsTransformer.lookup(item.typeAdapter.getDataTypeTypename());
			} else {
				transform = PrefsTransformer.lookup(item);
			}

			transform.generateWriteProperty(method, "editor", typeName(entity.getElement()), "bean", item);
			method.addCode("\n");
		}
		method.addCode("\n");
		method.addStatement("editor.commit()");
		builder.addMethod(method.build());
	}

	/**
	 * Generate read method.
	 *
	 * @param entity
	 *            the entity
	 */
	private static void generateReadMethod(PrefsEntity entity) {
		// read method
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("read").addModifiers(Modifier.PUBLIC).addJavadoc("read bean entirely\n\n").addJavadoc("@return read bean\n")
				.returns(typeName(entity.getName()));

		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addCode("\n");
			methodBuilder.addComment("initialize temporary variable for immutable POJO");
			ImmutableUtility.generateImmutableVariableInit(entity, methodBuilder);
		} else {
			methodBuilder.addStatement("$T bean=new $T()", typeName(entity.getName()), typeName(entity.getName()));
		}

		PrefsTransform transform;

		for (PrefsProperty item : entity.getCollection()) {
			if (item.hasTypeAdapter()) {
				transform = PrefsTransformer.lookup(item.typeAdapter.getDataTypeTypename());
			} else {
				transform = PrefsTransformer.lookup(item);
			}

			if (entity.isImmutablePojo()) {
				transform.generateReadProperty(methodBuilder, "prefs", typeName(item.getElement().asType()), null, item, true, ReadType.NONE);
			} else {
				transform.generateReadProperty(methodBuilder, "prefs", typeName(item.getElement().asType()), "bean", item, true, ReadType.NONE);
			}

			methodBuilder.addCode("\n");
		}

		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addComment("reset temporary variable for immutable POJO");
			ImmutableUtility.generateImmutableEntityCreation(entity, methodBuilder, "bean", true);
		}

		methodBuilder.addCode("\n");
		methodBuilder.addStatement("return bean");
		builder.addMethod(methodBuilder.build());
	}

	/**
	 * Generate single read method.
	 *
	 * @param entity
	 *            the entity
	 */
	private static void generateSingleReadMethod(PrefsEntity entity) {
		// read method
		PrefsTransform transform;
		Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

		for (PrefsProperty item : entity.getCollection()) {
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("get" + converter.convert(item.getName())).addModifiers(Modifier.PUBLIC)
					.addJavadoc("reads property <code>$L</code> from shared pref with key <code>$L</code>\n\n", item.getName(), item.getPreferenceKey())
					.addJavadoc("@return property $L value\n", item.getName()).returns(item.getPropertyType().getTypeName());

			if (item.hasTypeAdapter()) {
				transform = PrefsTransformer.lookup(item.typeAdapter.getDataTypeTypename());
			} else {
				transform = PrefsTransformer.lookup(item);
			}

			transform.generateReadProperty(methodBuilder, "prefs", typeName(item.getElement().asType()), "defaultBean", item, false, ReadType.RETURN);

			builder.addMethod(methodBuilder.build());
		}

	}
}
