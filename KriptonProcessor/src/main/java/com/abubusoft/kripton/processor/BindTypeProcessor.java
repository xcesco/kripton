/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.bind.BindEntityBuilder;
import com.abubusoft.kripton.processor.bind.BindTypeBuilder;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindModel;
import com.abubusoft.kripton.processor.core.AssertKripton;

/**
 * Annotation processor for json/xml/etc
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class BindTypeProcessor extends BaseProcessor {

	private BindModel model;

	private BindSharedPreferencesProcessor sharedPreferencesProcessor = new BindSharedPreferencesProcessor();

	private BindDataSourceProcessor dataSourceProcessor = new BindDataSourceProcessor();

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();

		annotations.add(BindType.class.getCanonicalName());
		annotations.addAll(sharedPreferencesProcessor.getSupportedAnnotationTypes());
		annotations.addAll(dataSourceProcessor.getSupportedAnnotationTypes());

		return annotations;
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);

		sharedPreferencesProcessor.init(processingEnv);
		dataSourceProcessor.init(processingEnv);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
			count++;
			if (count > 1) {
				return true;
			}

			model = new BindModel();
			int itemCounter = 0;

			parseBindType(roundEnv, elementUtils);

			// Build model
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				AssertKripton.assertTrueOrInvalidKindForAnnotationException(item.getKind() == ElementKind.CLASS, item, BindType.class);

				BindEntityBuilder.build(model, elementUtils, (TypeElement) item);
				itemCounter++;
			}

			if (itemCounter == 0) {
				info("No class with @BindType annotation was found");
			}
			
			// Generate classes for model
			for (BindEntity item : model.getEntities()) {
				BindTypeBuilder.generate(elementUtils, filer, item);
			}

			// after working with @BindType, we work with other annotations
			sharedPreferencesProcessor.process(annotations, roundEnv);
			dataSourceProcessor.process(annotations, roundEnv);

		} catch (Exception e) {
			String msg = e.getMessage();
			error(null, msg);

			if (DEBUG_MODE) {
				logger.log(Level.SEVERE, msg);
				e.printStackTrace();
			}
		}

		return true;
	}

}
