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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.element.GeneratedTypeElement;

/**
 * Annotation processor for json/xml/etc
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonProcessor extends BaseProcessor {

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

		public void addAll(Set<? extends Element> result) {
			for (Element element : result) {
				this.elements.add(element);
			}

		}
	}

	private ProcessedElement processedElement = new ProcessedElement();

	// private BindModel model;

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

	Pair<Set<GeneratedTypeElement>, Set<GeneratedTypeElement>> generatedPart = new Pair<>();

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		try {

			count++;

			if (count > 1) {
				return false;
			}

			// dump(1, roundEnv);

			// generate @BindGeneratedDao
			many2ManyProcessor.process(annotations, roundEnv);
			generatedPart = many2ManyProcessor.result;

			// generate bindmap
			typeProcessor.process(annotations, roundEnv);

			sharedPreferencesProcessor.process(annotations, roundEnv);
			sharedPreferencesProcessor.generateClasses();

			dataSourceProcessor.generatedPart = generatedPart;
			dataSourceProcessor.process(annotations, roundEnv);
			dataSourceProcessor.generatedClasses();

		} catch (Throwable e) {
			String msg = StringUtils.nvl(e.getMessage());
			error(null, e.getClass().getCanonicalName() + ": " + msg);
			/*
			 * StackTraceElement[] trace = e.getStackTrace();
			 * 
			 * StringWriter sw = new StringWriter(); e.printStackTrace(new
			 * PrintWriter(sw)); String exceptionAsString = sw.toString(); try
			 * (PrintWriter out = new PrintWriter("d:/filenameA.txt")) {
			 * out.println(exceptionAsString);
			 * 
			 * for (Element item : processedElement.elements) {
			 * out.println(String.format("processedElement %s",
			 * item.asType().toString())); }
			 * 
			 * for (Entry<String, TypeElement> entry :
			 * globalBeanElements.entrySet()) {
			 * out.println(String.format("GLOBAL %s = %s", entry.getKey(),
			 * entry.getValue().getSimpleName().toString())); }
			 * 
			 * out.println("dao---"); for (Entry<String, TypeElement> entry :
			 * this.dataSourceProcessor.globalBeanElements.entrySet()) {
			 * out.println(String.format("%s = %s", entry.getKey(),
			 * entry.getValue().getSimpleName().toString())); }
			 * 
			 * } catch (FileNotFoundException e1) { // TODO Auto-generated catch
			 * block e1.printStackTrace(); }
			 */
			if (DEBUG_MODE) {
				logger.log(Level.SEVERE, msg);
				e.printStackTrace();
			}
		}

		return true;
	}

	/*
	 * private void dump(int count, RoundEnvironment roundEnv) { try
	 * (PrintWriter out = new PrintWriter("d:/filename" + count + ".txt")) { for
	 * (Class<? extends Annotation> supportedAnnotation:
	 * getSupportedAnnotationClasses()) {
	 * out.println(String.format("annotation= %s\n--------------------\n",
	 * supportedAnnotation.getCanonicalName())); for (Element item :
	 * roundEnv.getElementsAnnotatedWith(supportedAnnotation)) {
	 * out.println(String.format("processedElement %s",
	 * ((TypeElement)item).getQualifiedName().toString())); } }
	 * 
	 * } catch (FileNotFoundException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } }
	 */

}
