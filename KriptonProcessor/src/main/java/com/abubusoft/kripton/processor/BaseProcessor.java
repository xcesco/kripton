package com.abubusoft.kripton.processor;

import java.util.HashSet;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;

public abstract class BaseProcessor extends AbstractProcessor {

	protected int count;

	protected HashSet<String> excludedMethods;

	protected Types typeUtils;

	/**
	 * for development scope
	 */
	public static boolean DEVELOP_MODE = false;

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
	 * @see javax.annotation.processing.AbstractProcessor#getSupportedSourceVersion()
	 */
	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	/**
	 * logger
	 */
	protected static Logger logger = Logger.getGlobal();

	protected Elements elementUtils;
	protected Filer filer;
	protected Messager messager;

	protected void info(String msg, Object... args) {
		if (DEVELOP_MODE) {
			logger.info(String.format(msg, args));
		}
		messager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));

	}

	protected void error(Element e, String msg, Object... args) {
		messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
	}

	protected void warn(String msg, Object... args) {
		if (DEVELOP_MODE) {
			logger.warning(String.format(msg, args));
		}
		messager.printMessage(Diagnostic.Kind.WARNING, String.format(msg, args));
	}

}
