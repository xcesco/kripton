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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.bind.BindEntityBuilder;
import com.abubusoft.kripton.processor.bind.BindTypeBuilder;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindModel;
import com.abubusoft.kripton.processor.core.AssertKripton;

/**
 * Annotation processor for json/xml/etc
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindTypeProcessor extends BaseProcessor {

	public static class ProcessedElement implements RoundEnvironment {
		HashSet<Element> elements = new HashSet<>();

		public void addRound(RoundEnvironment round) {
			this.elements.addAll(round.getRootElements());
		}

		public Set<? extends Element> getElementsAnnotatedWith(Class<? extends Annotation> annotation) {
			HashSet<Element> result = new HashSet<Element>();

			for (Element element : this.elements) {
				if (element.getAnnotation(annotation) != null) {
					result.add(element);
				}
			}

			return result;
		}

		@Override
		public boolean processingOver() {
			return false;
		}

		@Override
		public boolean errorRaised() {
			return false;
		}

		@Override
		public Set<? extends Element> getRootElements() {
			HashSet<Element> result = new HashSet<Element>();

			for (Element element : this.elements) {
				result.add(element);
			}

			return result;
		}

		@Override
		public Set<? extends Element> getElementsAnnotatedWith(TypeElement a) {
			return null;
		}
		}

	private ProcessedElement processedElement = new ProcessedElement();

	private BindModel model;

	private BindMany2ManySubProcessor many2ManyProcessor = new BindMany2ManySubProcessor();

	private BindSharedPreferencesSubProcessor sharedPreferencesProcessor = new BindSharedPreferencesSubProcessor();

	private BindDataSourceSubProcessor dataSourceProcessor = new BindDataSourceSubProcessor();

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();

		annotations.add(BindType.class.getCanonicalName());
		annotations.addAll(sharedPreferencesProcessor.getSupportedAnnotationTypes());
		annotations.addAll(dataSourceProcessor.getSupportedAnnotationTypes());
		annotations.addAll(many2ManyProcessor.getSupportedAnnotationTypes());

		return annotations;
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);

		many2ManyProcessor.init(processingEnv);
		sharedPreferencesProcessor.init(processingEnv);
		dataSourceProcessor.init(processingEnv);
	}

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		try {
			count++;
			if (count == 1) {
				many2ManyProcessor.process(annotations, roundEnv);
			}

			if (roundEnv.getRootElements().size() > 0) {
				processedElement.addRound(roundEnv);
				return true;
			}

			model = new BindModel();
			final AtomicInteger itemCounter = new AtomicInteger();
			itemCounter.set(0);

			parseBindType(processedElement);

			// Build model
			for (Element element : processedElement.getElementsAnnotatedWith(BindType.class)) {
				final Element item = element;

				AssertKripton.assertTrueOrInvalidKindForAnnotationException(item.getKind() == ElementKind.CLASS, item, BindType.class);
				BindEntityBuilder.parse(model, (TypeElement) item);
				itemCounter.addAndGet(1);
			}

			if (itemCounter.get() == 0) {
				info("No class with @%s annotation was found", BindType.class.getSimpleName());
			}

			// Generate classes for model
			generateFromModel();

			sharedPreferencesProcessor.process(annotations, processedElement);
			dataSourceProcessor.process(annotations, processedElement);
			
			sharedPreferencesProcessor.generateClasses();
			dataSourceProcessor.generatedClasses();
		} catch (Exception e) {
			String msg = StringUtils.nvl(e.getMessage());
			error(null, msg);

			if (DEBUG_MODE) {
				logger.log(Level.SEVERE, msg);
				e.printStackTrace();
			}
		}

		return true;
	}

	/**
	 * @throws IOException
	 */
	private void generateFromModel() throws IOException {
		for (BindEntity entity : model.getEntities()) {
			final BindEntity item = entity;

			BindTypeBuilder.generate(filer, item);
		}
	}

}
