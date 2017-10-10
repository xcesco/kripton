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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MEntity;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MModel;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.sqlite.BindM2MBuilder;

/**
 * Annotation processor for shared preferences
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindMany2ManyProcessor extends BaseProcessor {

	private M2MModel model;

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
		
		annotations.add(BindDaoMany2Many.class.getCanonicalName());
		//annotations.add(BindDataSource.class.getCanonicalName());
		
		//annotations.add(BindDaoGeneratedPart.class.getCanonicalName());

		return annotations;
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
	}
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {			
			model = new M2MModel();
			int itemCounter = 0;

			//parseBindType(roundEnv, elementUtils);

			// Put all @BindSharedPreferences elements in beanElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindDaoMany2Many.class)) {
				if (item.getKind() != ElementKind.INTERFACE) {
					String msg = String.format("%s %s, only interface can be annotated with @%s annotation", item.getKind(), item, BindDaoMany2Many.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}

				M2MEntity entity = analyzeEntity(item);
				
				model.entityAdd(entity);				
				itemCounter++;
			}

			if (itemCounter > 0) {
				BindM2MBuilder.generate(filer, model);
			}

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

	/**
	 * @param item
	 * @return
	 */
	private M2MEntity analyzeEntity(Element item) {
		M2MEntity entity=M2MEntity.extractEntityManagedByDAO(item);
		return entity;
	}
	
}
