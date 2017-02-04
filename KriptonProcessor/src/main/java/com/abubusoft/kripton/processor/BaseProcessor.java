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
package com.abubusoft.kripton.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;

public abstract class BaseProcessor extends AbstractProcessor {

	protected int count;

	/**
	 * build bindType elements map
	 * 
	 * @param roundEnv
	 */
	protected void parseBindType(RoundEnvironment roundEnv) {
		// Put all @BindType elements in beanElements
		globalBeanElements.clear();
		for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
			if (!(item.getKind() == ElementKind.CLASS || item.getKind() == ElementKind.ENUM)) {
				String msg = String.format("%s %s, only class can be annotated with @%s annotation", item.getKind(), item, BindType.class.getSimpleName());
				throw (new InvalidKindForAnnotationException(msg));
			}
			globalBeanElements.put(item.toString(), item);
		}

	}

	/**
	 * define which annotation the annotation processor is interested in
	 */
	protected final Map<String, Element> globalBeanElements = new HashMap<String, Element>();

	protected HashSet<String> excludedMethods;

	protected Types typeUtils;

	/**
	 * if true we are in a test 
	 */
	public static boolean TEST_MODE = false;

	/**
	 * if we want to display debug info
	 */
	public static boolean DEBUG_MODE = false;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);

		AnnotationProcessorUtilis.init(processingEnv.getMessager());

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
	 * logger
	 */
	protected static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	protected Elements elementUtils;
	protected Filer filer;
	protected Messager messager;

	protected void info(String msg, Object... args) {
		messager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
	}

	protected void error(Element e, String msg, Object... args) {
		// this must be always enabled, due control annotation processor execution status (if display an error, compiler fails).
		messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
	}

	/**
	 * Display warn message
	 * 
	 * @param msg
	 * @param args
	 */
	protected void warn(String msg, Object... args) {
		messager.printMessage(Diagnostic.Kind.WARNING, String.format(msg, args));
	}

}
