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
/**
 * 
 */
package com.abubusoft.kripton.processor;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

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
import com.abubusoft.kripton.processor.bind.transform.BindTransformer;
import com.abubusoft.kripton.processor.bind.transform.EnumBindTransform;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;

/**
 * Annotation processor for json/xml/etc
 * 
 * @author xcesco
 *
 */
public class BindTypeProcessor extends BaseProcessor {

	private BindModel model;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes
	 * ()
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();

		annotations.add(BindType.class.getCanonicalName());

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

			model = new BindModel();
			int itemCounter = 0;

			parseBindType(roundEnv);
			for (Element item : globalBeanElements.values()) {
				if (item.getKind() == ElementKind.ENUM) {
					BindTransformer.register(typeName(item), new EnumBindTransform(typeName(item)));
				}
			}

			// Put all @BindSharedPreferences elements in beanElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				if (item.getKind() != ElementKind.CLASS && item.getKind() != ElementKind.ENUM) {
					String msg = String.format("%s %s, only class can be annotated with @%s annotation", item.getKind(), item, BindType.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}

				if (item.getKind() == ElementKind.ENUM)
					continue;

				BindEntityBuilder.build(model, elementUtils,  item);

				itemCounter++;
			}

			if (itemCounter == 0) {
				warn("No class with %s annotation was found", BindType.class);
			}

			for (BindEntity item : model.getEntities()) {
				BindTypeBuilder.generate(elementUtils, filer, item);
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

}
