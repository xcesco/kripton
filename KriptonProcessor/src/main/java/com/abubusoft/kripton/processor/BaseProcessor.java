package com.abubusoft.kripton.processor;

import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

public abstract class BaseProcessor extends AbstractProcessor {

	/**
	 * for development scope
	 */
	public static boolean DEVELOP_MODE = false;

	/**
	 * logger
	 */
	protected static Logger logger = Logger.getGlobal();

	protected Elements elementUtils;
	protected Filer filer;
	protected Messager messager;

	/**
	 * Display message. Used only in develop mode
	 * 
	 * @param msg
	 *            message to display
	 */
	public static void info(String msg) {
		if (DEVELOP_MODE) {
			logger.info(msg);
		}

	}

	protected void error(Element e, String msg, Object... args) {
		messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
	}

}
