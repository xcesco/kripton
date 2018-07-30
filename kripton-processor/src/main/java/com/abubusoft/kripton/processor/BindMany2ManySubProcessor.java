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

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MEntity;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MModel;
import com.abubusoft.kripton.processor.element.GeneratedTypeElement;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.sqlite.BindM2MBuilder;

/**
 * Annotation processor for shared preferences.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class BindMany2ManySubProcessor extends BaseProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.BaseProcessor#clear()
	 */
	public void clear() {
		super.clear();

		result = null;
		model = null;
	}

	/** The model. */
	private M2MModel model;

	/** The result. */
	public Pair<Set<GeneratedTypeElement>, Set<GeneratedTypeElement>> result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.BaseProcessor#
	 * getSupportedAnnotationClasses()
	 */
	protected Set<Class<? extends Annotation>> getSupportedAnnotationClasses() {
		Set<Class<? extends Annotation>> annotations = new LinkedHashSet<Class<? extends Annotation>>();

		annotations.add(BindDaoMany2Many.class);

		return annotations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
	 * javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		model = new M2MModel();

		for (Element daoItem : roundEnv.getElementsAnnotatedWith(BindDaoMany2Many.class)) {
			if (daoItem.getKind() != ElementKind.INTERFACE) {
				String msg = String.format("%s %s, only interface can be annotated with @%s annotation",
						daoItem.getKind(), daoItem, BindDaoMany2Many.class.getSimpleName());
				throw (new InvalidKindForAnnotationException(msg));
			}

			M2MEntity entity = M2MEntity.extractEntityManagedByDAO((TypeElement) daoItem);

			model.entityAdd(entity);
		}

		result = BindM2MBuilder.generate(filer, model);

		return true;
	}

	public void generateClasses() {

	}

}
