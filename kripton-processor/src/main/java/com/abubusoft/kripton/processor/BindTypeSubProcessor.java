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
package com.abubusoft.kripton.processor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.bind.BindEntityBuilder;
import com.abubusoft.kripton.processor.bind.BindTypeBuilder;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindModel;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.abubusoft.kripton.processor.bind.transform.BindTransformer;
import com.abubusoft.kripton.processor.bind.transform.ObjectBindTransform;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.exceptions.KriptonNoAnnotatedClassException;
import com.squareup.javapoet.TypeName;

/**
 * Annotation processor for json/xml/etc
 * 
 * <p>
 * This processor is one-step processor.
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindTypeSubProcessor extends BaseProcessor {

	/** The model. */
	private BindModel model = new BindModel();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.BaseProcessor#getSupportedAnnotationClasses()
	 */
	protected Set<Class<? extends Annotation>> getSupportedAnnotationClasses() {
		Set<Class<? extends Annotation>> annotations = new LinkedHashSet<Class<? extends Annotation>>();
		annotations.add(BindType.class);

		return annotations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
	 * javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		parseBindType(roundEnv);

		// Build model
		for (Element element : roundEnv.getElementsAnnotatedWith(BindType.class)) {
			final Element item = element;

			AssertKripton.assertTrueOrInvalidKindForAnnotationException(item.getKind() == ElementKind.CLASS, item,
					BindType.class);
			BindEntityBuilder.parse(model, (TypeElement) item);
		}

		if (globalBeanElements.size() == 0) {
			info("No class with @%s annotation was found", BindType.class.getSimpleName());
		}

		// check
		checkEntities();

		// Generate classes for model
		try {
			generateClasses();
		} catch (IOException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}

		return true;
	}

	void checkEntities() {
		StringBuilder builder = new StringBuilder();
		String separator = "";
		boolean unannotatedClasses = false;
		for (BindEntity entity : model.getEntities()) {
			for (BindProperty property : entity.getCollection()) {
				TypeName typeName = property.getPropertyType().getTypeName();
				BindTransform noBindIsNeeded = BindTransformer.lookup(typeName);

				if ((noBindIsNeeded instanceof ObjectBindTransform) && !model.hasEntityOfType(typeName)) {
					unannotatedClasses = true;
					builder.append(separator);
					builder.append("'");
					builder.append(typeName.toString());
					builder.append("'");
					
					separator = ", ";
					error(null,
							"'%s' is used in field declaration in '%s' and needs to be annotated with Kripton @%s annotation",
							typeName.toString(), entity.getElement().asType().toString(),
							BindType.class.getSimpleName());
				}
			}

		}

		if (unannotatedClasses) {
			throw new KriptonNoAnnotatedClassException(builder.toString());
		}
	}

	/**
	 * Generate classes.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void generateClasses() throws IOException {
		for (BindEntity entity : model.getEntities()) {
			BindTypeBuilder.generate(filer, entity);
		}
	}

}
