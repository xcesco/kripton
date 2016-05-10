package com.abubusoft.kripton.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.android.annotation.SQLDao;
import com.abubusoft.kripton.android.annotation.SQLDatabaseSchema;
import com.abubusoft.kripton.android.annotation.SQLDelete;
import com.abubusoft.kripton.android.annotation.SQLDeleteBean;
import com.abubusoft.kripton.android.annotation.SQLInsert;
import com.abubusoft.kripton.android.annotation.SQLInsertBean;
import com.abubusoft.kripton.android.annotation.SQLSelect;
import com.abubusoft.kripton.android.annotation.SQLSelectBean;
import com.abubusoft.kripton.android.annotation.SQLSelectBeanList;
import com.abubusoft.kripton.android.annotation.SQLSelectScalar;
import com.abubusoft.kripton.android.annotation.SQLUpdate;
import com.abubusoft.kripton.android.annotation.SQLUpdateBean;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFoundListener;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.abubusoft.kripton.processor.core.reflect.MethodUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.sqlite.CursorGenerator;
import com.abubusoft.kripton.processor.sqlite.DaoGenerator;
import com.abubusoft.kripton.processor.sqlite.DatabaseGenerator;
import com.abubusoft.kripton.processor.sqlite.TableGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidSQLDaoDefinitionException;
import com.abubusoft.kripton.processor.sqlite.exceptions.SQLPrimaryKeyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.SQLPrimaryKeyNotValidTypeException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.DaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModel;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.utils.StringUtility;

public class BindDatabaseProcessor extends AbstractProcessor {

	Logger logger = Logger.getGlobal();

	private Elements elementUtils;

	private Filer filer;

	private Messager messager;

	private SQLEntity currentEntity;

	private SQLiteDatabaseSchema currentSchema;

	private SQLiteModel model;

	private void error(Element e, String msg, Object... args) {
		messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes ()
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();

		annotations.add(SQLDatabaseSchema.class.getCanonicalName());
		annotations.add(BindType.class.getCanonicalName());
		annotations.add(SQLDao.class.getCanonicalName());

		return annotations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#getSupportedSourceVersion()
	 */
	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#init(javax.annotation. processing.ProcessingEnvironment)
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		elementUtils = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		messager = processingEnv.getMessager();

		model = new SQLiteModel();
	}

	private int count;

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		try {

			count++;
			if (count > 1) {
				logger.info("============================================== " + count);
				return true;
			}

			model.schemaClear();

			Map<String, Element> bindElements = new HashMap<String, Element>();

			// Get all bind type
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				if (item.getKind() != ElementKind.CLASS) {
					error(item, "Only class can be annotated with @%s annotation", BindType.class.getSimpleName());
					return true;
				}
				bindElements.put(item.toString(), item);
			}

			ModelProperty property;
			// Get all database schema definitions
			for (Element item : roundEnv.getElementsAnnotatedWith(SQLDatabaseSchema.class)) {
				if (item.getKind() != ElementKind.INTERFACE) {
					error(item, "Only interfaces can be annotated with @%s annotation", SQLDatabaseSchema.class.getSimpleName());
					return true;
				}

				// get all entity used within SQLDatabaseSchema annotation
				List<String> classesIntoDatabase = AnnotationUtility.extractAsClassNameArray(elementUtils, item, SQLDatabaseSchema.class, AnnotationAttributeType.ATTRIBUTE_VALUE);

				String schemaFileName = AnnotationUtility.extractAsString(elementUtils, item, SQLDatabaseSchema.class, AnnotationAttributeType.ATTRIBUTE_FILENAME);
				int schemaVersion = AnnotationUtility.extractAsInt(elementUtils, item, SQLDatabaseSchema.class, AnnotationAttributeType.ATTRIBUTE_VERSION);

				currentSchema = new SQLiteDatabaseSchema((TypeElement) item, schemaFileName, schemaVersion);
				model.schemaAdd(currentSchema);

				// define which annotation the annotation processor is interested in
				AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindAllFields.class).build();
				AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindColumn.class).build();
				// for each entity used in database
				for (String c : classesIntoDatabase) {
					if (bindElements.containsKey(c)) {
						TypeElement classElement = (TypeElement) bindElements.get(c);

						currentEntity = new SQLEntity(classElement);
						AnnotationUtility.buildAnnotations(elementUtils, currentEntity, classAnnotationFilter);

						if (currentEntity.containsAnnotation(BindAllFields.class)) {
							PropertyUtility.buildProperties(elementUtils, currentEntity, propertyAnnotationFilter);
						} else {
							PropertyUtility.buildProperties(elementUtils, currentEntity, propertyAnnotationFilter, new PropertyCreatedListener() {

								@Override
								public boolean onProperty(ModelProperty property) {
									if (property.getAnnotation(Bind.class) != null) {
										return true;
									}
									return false;
								}
							});
						}

						// check primary key
						property = currentEntity.getPrimaryKey();
						if (property == null)
							throw (new SQLPrimaryKeyNotFoundException(currentEntity));

						if (!property.isType(Long.TYPE, Long.class))
							throw (new SQLPrimaryKeyNotValidTypeException(currentEntity, property));

						currentSchema.entityAdd(currentEntity);
					}
				}
			}

			if (model.schemaCount() > 1) {
				// TODO improve schema management (more than one)
				error(null, "Only one interface can be defined in project @%s annotation", SQLDatabaseSchema.class.getSimpleName());
				return true;
			}

			// define methods to ignore
			final Set<String> excludedMethods = new HashSet<String>();
			excludedMethods.add("wait");
			excludedMethods.add("notifyAll");
			excludedMethods.add("notify");
			excludedMethods.add("toString");
			excludedMethods.add("equals");
			excludedMethods.add("hashCode");
			excludedMethods.add("getClass");

			// Get all dao definitions
			for (Element daoItem : roundEnv.getElementsAnnotatedWith(SQLDao.class)) {
				if (daoItem.getKind() != ElementKind.INTERFACE) {
					error(daoItem, "Only interfaces can be annotated with @%s annotation", SQLDao.class.getSimpleName());
					return true;
				}

				String entityClassName = AnnotationUtility.extractAsClassName(elementUtils, daoItem, SQLDao.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
				final DaoDefinition currentDaoDefinition = new DaoDefinition((TypeElement) daoItem, entityClassName);

				if (!bindElements.containsKey(currentDaoDefinition.getEntityClassName())) {
					throw (new InvalidSQLDaoDefinitionException(currentDaoDefinition));
				}

				currentSchema.add(currentDaoDefinition);
				logger.info("Dao... " + currentDaoDefinition.getName() + " " + entityClassName + " " + bindElements.containsKey(currentDaoDefinition.getEntityClassName()));

				// create method for dao
				MethodUtility.forEachMethods(elementUtils, (TypeElement) daoItem, new MethodFoundListener() {

					@Override
					public void onMethod(ExecutableElement element) {
						if (excludedMethods.contains(element.getSimpleName().toString()))
							return;

						// add method
						final SQLiteModelMethod currentMethod = new SQLiteModelMethod((ExecutableElement) element);
						currentDaoDefinition.add(currentMethod);

						AnnotationUtility.forEachAnnotations(elementUtils, element, new AnnotationFoundListener() {


							@Override
							public void onAnnotation(Element element, String annotationClassName, Map<String, String> attributes) {

								if //@formatter:off
									(										
										   annotationClassName.equals(SQLInsert.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLInsertBean.class.getCanonicalName())
										
										|| annotationClassName.equals(SQLUpdate.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLUpdateBean.class.getCanonicalName())
										
										|| annotationClassName.equals(SQLDelete.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLDeleteBean.class.getCanonicalName())
										
										|| annotationClassName.equals(SQLSelect.class.getCanonicalName())
										|| annotationClassName.equals(SQLSelectBean.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLSelectBeanList.class.getCanonicalName()) 
										|| annotationClassName.equals(SQLSelectScalar.class.getCanonicalName())														
									)	
									//@formatter:on
								{
								} else {
									return;
								}

								ModelAnnotation annotation = new ModelAnnotation(annotationClassName, attributes);
								currentMethod.addAnnotation(annotation);

								logger.info("Annotation... " + annotation);
								logger.info("Method... " + currentMethod.toString());
							}
						});

					}

				});
			}

			// generate table java
			TableGenerator.generate(elementUtils, filer, currentSchema);
			DaoGenerator.generate(elementUtils, filer, currentSchema);
			CursorGenerator.generate(elementUtils, filer, currentSchema);
			DatabaseGenerator.generate(elementUtils, filer, currentSchema);

			logger.info(currentSchema.toString());
		} catch (Exception e) {
			e.printStackTrace();

			error(null, e.getMessage());
		}

		return true;
	}

	protected void analyzeClassHierarchy(StringBuilder buffer, TypeElement clazz, int level) {
		String name = clazz.getQualifiedName().toString();
		// se siamo arrivati all'object, usciamo
		if (name.equals(Object.class.getCanonicalName()))
			return;

		String pad = StringUtility.repeatString("  ", level);
		buffer.append(pad + " " + name + "\n");

		TypeMirror superClassTypeMirror = clazz.getSuperclass();
		TypeElement superClassTypeElement = (TypeElement) ((DeclaredType) superClassTypeMirror).asElement();
		analyzeClassHierarchy(buffer, superClassTypeElement, level + 1);
	}

	protected void analyzeDeclaredField(StringBuilder buffer, TypeElement clazz, int level) throws MappingException, WriterException {
		List<? extends Element> list = elementUtils.getAllMembers(clazz);

		String pad = StringUtility.repeatString("  ", level);

		// elenco completo
		for (Element item : list) {
			{
				buffer.append(pad + " - " + item.getKind() + " " + item.getSimpleName() + " [" + item.asType() + "] " + item.getModifiers() + "\n");
			}
		}

		PropertyUtility.buildProperties(elementUtils, currentEntity, null);

		BinderWriter writer = BinderFactory.getJSONWriter(BinderOptions.build().indent(true));

		logger.info(writer.write(currentEntity));

	}

}
