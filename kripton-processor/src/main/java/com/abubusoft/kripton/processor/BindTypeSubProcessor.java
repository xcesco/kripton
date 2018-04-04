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
import com.abubusoft.kripton.processor.core.AssertKripton;

/**
 * Annotation processor for json/xml/etc
 * 
 * <p>This processor is one-step processor.</p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindTypeSubProcessor extends BaseProcessor {

	private BindModel model=new BindModel();

	protected Set<Class<? extends Annotation>> getSupportedAnnotationClasses() {
		Set<Class<? extends Annotation>> annotations = new LinkedHashSet<Class<? extends Annotation>>();
		annotations.add(BindType.class);		

		return annotations;
	}

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		parseBindType(roundEnv);
		
		// Build model
		for (Element element : roundEnv.getElementsAnnotatedWith(BindType.class)) {
			final Element item = element;

			AssertKripton.assertTrueOrInvalidKindForAnnotationException(item.getKind() == ElementKind.CLASS, item, BindType.class);
			BindEntityBuilder.parse(model, (TypeElement) item);
		}
		

		if (globalBeanElements.size() == 0) {
			info("No class with @%s annotation was found", BindType.class.getSimpleName());
		}

		// Generate classes for model
		try {
			generateClasses();
		} catch (IOException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e));
		}

		return true;
	}
	
	

	/**
	 * @throws IOException
	 */
	private void generateClasses() throws IOException {
		for (BindEntity entity : model.getEntities()) {
			final BindEntity item = entity;

			BindTypeBuilder.generate(filer, item);
		}
	}

}
