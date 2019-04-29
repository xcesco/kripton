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
package com.abubusoft.kripton.processor;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
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
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.core.AssertKripton;

/**
 * The Class BaseProcessor.
 */
public abstract class BaseProcessor extends AbstractProcessor {

	/** if we want to display debug info. */
	public static boolean DEBUG_MODE = false;
	
	/** if we want to display debug info. */
	public static boolean LOG_GENERATION_ENABLED_MODE = true;

	/** The element utils. */
	public static Elements elementUtils;
	
	/** if true we are in a test. */
	public static boolean JUNIT_TEST_MODE = false;
	
	/** The logger. */
	protected static Logger logger = Logger.getGlobal();

	/** The count. */
	protected int count;
	
	/** The excluded methods. */
	protected HashSet<String> excludedMethods;

	/** The filer. */
	protected Filer filer;

	/** define which annotation the annotation processor is interested in. */
	protected final Map<String, TypeElement> globalBeanElements = new HashMap<String, TypeElement>();

	/** The messager. */
	protected static Messager messager;

	/** The type utils. */
	protected Types typeUtils;

	/**
	 * Clear.
	 */
	public void clear() {		
		// Put all @BindType elements in beanElements
		globalBeanElements.clear();
		
	}
	
	/**
	 * Error.
	 *
	 * @param e the e
	 * @param msg the msg
	 * @param args the args
	 */
	public void error(Element e, String msg, Object... args) {
		// this must be always enabled, due control annotation processor
		// execution status (if display an error, compiler fails).
		messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);		
	}

	/**
	 * Filter.
	 *
	 * @param roundEnv the round env
	 * @return the sets the
	 */
	public Set<Element> filter(RoundEnvironment roundEnv) {
		Set<Element> result=new HashSet<Element>();		
		for (Class<? extends Annotation> annotation: getSupportedAnnotationClasses()) {
			result.addAll(roundEnv.getElementsAnnotatedWith(annotation));	
		}
		
		return result;
		
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gets the supported annotation classes.
	 *
	 * @return the supported annotation classes
	 */
	protected abstract Set<Class<? extends Annotation>> getSupportedAnnotationClasses();

	/* (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes()
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {		
		Set<String> result=new HashSet<String>();		
		for (Class<? extends Annotation> annotation: getSupportedAnnotationClasses()) {
			result.add(annotation.getCanonicalName());	
		}
				
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.annotation.processing.AbstractProcessor#getSupportedSourceVersion()
	 */
	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
	
	/**
	 * Checks for work in this round.
	 *
	 * @param roundEnv the round env
	 * @return true, if successful
	 */
	public boolean hasWorkInThisRound(RoundEnvironment roundEnv) {
		if (this.filter(roundEnv).size()>0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Info.
	 *
	 * @param msg the msg
	 * @param args the args
	 */
	public static void info(String msg, Object... args) {
		if (BaseProcessor.JUNIT_TEST_MODE) {
			logger.info(String.format(msg, args));
		}

		if (!BaseProcessor.JUNIT_TEST_MODE && BaseProcessor.DEBUG_MODE) {
			messager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#init(javax.annotation.processing.ProcessingEnvironment)
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
	
		elementUtils = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		messager = processingEnv.getMessager();		
		typeUtils = processingEnv.getTypeUtils();

		// define methods to ignore
		excludedMethods = new HashSet<String>();
		excludedMethods.add("wait");
		excludedMethods.add("notifyAll");
		excludedMethods.add("notify");
		excludedMethods.add("toString");
		excludedMethods.add("equals");
		excludedMethods.add("hashCode");
		excludedMethods.add("getClass");
		
		count=0;
		clear();
	}

	/**
	 * build bindType elements map.
	 *
	 * @param roundEnv the round env
	 */
	protected void parseBindType(RoundEnvironment roundEnv) {		
		for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
			AssertKripton.assertTrueOrInvalidKindForAnnotationException(item.getKind() == ElementKind.CLASS, item, BindType.class);

			globalBeanElements.put(item.toString(), (TypeElement) item);
		}

	}

}
