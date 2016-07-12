/**
 * 
 */
package com.abubusoft.kripton.processor;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.PropertyFactory;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.sharedprefs.BindSharedPreferencesBuilder;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefEntity;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefModel;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefProperty;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.sqlite.exceptions.NoAnnotationFoundException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.TypeName;

/**
 * Annotation processor for shared preferences
 * 
 * @author xcesco
 *
 */
public class BindSharedPreferencesProcessor extends BaseProcessor {

	private PrefModel model;

	private AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindAllFields.class).add(BindSharedPreferences.class).build();
	private AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindPreference.class).build();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes ()
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();

		annotations.add(BindSharedPreferences.class.getCanonicalName());

		return annotations;
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
			count++;
			if (count > 1) {
				return true;
			}

			model = new PrefModel();
			int itemCounter = 0;

			// Put all @BindSharedPreferences elements in beanElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindSharedPreferences.class)) {
				if (item.getKind() != ElementKind.CLASS) {
					String msg = String.format("Only class can be annotated with @%s annotation", BindSharedPreferences.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}

				createSharedPreferences(item);							

				itemCounter++;
			}

			if (itemCounter == 0) {
				warn("No class with %s annotation was found", BindSharedPreferences.class);
			}

			String generatedName;
			for (PrefEntity item: model.getEntities())
			{				
				generatedName=BindSharedPreferencesBuilder.generate(elementUtils, filer, item);
				info("Processing annotation %s generate: %s", BindSharedPreferences.class.getSimpleName(), generatedName);
			}

		} catch (Exception e) {
			String msg = e.getMessage();
			error(null, msg);

			if (DEVELOP_MODE) {
				logger.log(Level.SEVERE, msg);
				e.printStackTrace();
			}
		}

		return true;
	}

	private String createSharedPreferences(Element sharedPreference) {
		Element beanElement = sharedPreference;
		String result=beanElement.getSimpleName().toString();
		
		final PrefEntity currentEntity = new PrefEntity(beanElement.getSimpleName().toString(), (TypeElement) beanElement);

		AnnotationUtility.buildAnnotations(elementUtils, currentEntity, classAnnotationFilter);

		final boolean bindAllFields = currentEntity.containsAnnotation(BindAllFields.class);
		{
			PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<PrefProperty>() {

				@Override
				public PrefProperty createProperty(Element element) {
					return new PrefProperty(element);
				}
			}, propertyAnnotationFilter, new PropertyCreatedListener<PrefProperty>() {

				@Override
				public boolean onProperty(PrefProperty property) {
					if (!bindAllFields && (property.getAnnotation(Bind.class) == null && property.getAnnotation(BindColumn.class) != null)) {
						String msg = String.format("In class '%s', property '%s' needs '%s' annotation", currentEntity.getSimpleName(), property.getName(), Bind.class.getSimpleName());
						throw (new NoAnnotationFoundException(msg));
					}

					if (bindAllFields || (property.getAnnotation(Bind.class)) != null) {
						TypeName name = TypeName.get(property.getElement().asType());

						LiteralType lt = LiteralType.of(name.toString());

						if (lt.isComposed()) {
							String msg = String.format("In class '%s', property '%s' is ignored in database build because it is composed", currentEntity.getSimpleName(), property.getName());
							info(msg);
							return false;
						}

						ModelAnnotation annotation = property.getAnnotation(BindPreference.class);
						if (annotation != null) {
							String key = AnnotationUtility.extractAsString(elementUtils, property.getElement(), BindPreference.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
						}

						return true;
					}

					return false;
				}
			});

			model.entityAdd(currentEntity);
			return result;
		}

	}

}
