package com.abubusoft.kripton.processor;

import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

public abstract class BaseProcessor extends AbstractProcessor {

	/**
	 * for development scope
	 */
	public static boolean DEVELOP_MODE = false;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		
		elementUtils = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		messager = processingEnv.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * logger
	 */
	protected static Logger logger = Logger.getGlobal();

	protected Elements elementUtils;
	protected Filer filer;
	protected Messager messager;
	
	protected void info(String msg, Object ... args) {
		if (DEVELOP_MODE) {
			logger.info(msg);
		}
		messager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));

	}

	protected void error(Element e, String msg, Object... args) {
		messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
	}

}
