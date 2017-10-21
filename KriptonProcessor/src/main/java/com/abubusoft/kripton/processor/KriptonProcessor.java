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

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;

/**
 * Annotation processor for json/xml/etc
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonProcessor extends BaseProcessor {
	private BindMany2ManySubProcessor many2ManyProcessor = new BindMany2ManySubProcessor();

	private BindSharedPreferencesSubProcessor sharedPreferencesProcessor = new BindSharedPreferencesSubProcessor();

	private BindDataSourceSubProcessor dataSourceProcessor = new BindDataSourceSubProcessor();

	private BindTypeSubProcessor typeProcessor = new BindTypeSubProcessor();

	protected Set<Class<? extends Annotation>> getSupportedAnnotationClasses() {
		Set<Class<? extends Annotation>> annotations = new LinkedHashSet<Class<? extends Annotation>>();

		annotations.addAll(typeProcessor.getSupportedAnnotationClasses());
		annotations.addAll(sharedPreferencesProcessor.getSupportedAnnotationClasses());
		annotations.addAll(dataSourceProcessor.getSupportedAnnotationClasses());
		annotations.addAll(many2ManyProcessor.getSupportedAnnotationClasses());

		return annotations;
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);

		typeProcessor.init(processingEnv);
		many2ManyProcessor.init(processingEnv);
		sharedPreferencesProcessor.init(processingEnv);
		dataSourceProcessor.init(processingEnv);

		count = 0;
		JavaWriterHelper.reset();
	}

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		try {
			count++;			
			if (count ==1) {


				many2ManyProcessor.clear();
				typeProcessor.clear();
				sharedPreferencesProcessor.clear();
				dataSourceProcessor.clear();

				// generate @BindGeneratedDao
				many2ManyProcessor.process(annotations, roundEnv);

				// generate bindmap
				typeProcessor.process(annotations, roundEnv);

				sharedPreferencesProcessor.process(annotations, roundEnv);
				sharedPreferencesProcessor.generateClasses();

				dataSourceProcessor.generatedEntities = many2ManyProcessor.result.value0;
				dataSourceProcessor.generatedDaos = many2ManyProcessor.result.value1;
				// dump(1, roundEnv);
				dataSourceProcessor.analyzeRound(annotations, roundEnv);
				dataSourceProcessor.process(annotations, roundEnv);
				dataSourceProcessor.generatedClasses(roundEnv);
			} else if (count==2) {
				dataSourceProcessor.analyzeSecondRound(annotations, roundEnv);
				dataSourceProcessor.processSecondRound(annotations, roundEnv);
				dataSourceProcessor.generatedClassesSecondRound(roundEnv);
			}

			return false;
		} catch (Throwable e) {
			String msg = StringUtils.nvl(e.getMessage());
			error(null, e.getClass().getCanonicalName() + ": " + msg);
			
			if (DEBUG_MODE) {
				logger.log(Level.SEVERE, msg);
				e.printStackTrace();
			}
		}

		return false;
	}

}
