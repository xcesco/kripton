package com.abubusoft.kripton.processor;

import java.io.File;

import javax.annotation.processing.ProcessingEnvironment;

import com.abubusoft.kripton.common.StringUtils;

public abstract class KriptonOptions {
	public static String DEBUG_OPTION_NAME = "kripton.debug";
	public static String SCHEMA_LOCATION_OPTION_NAME = "kripton.schemaLocation";
	public static String ANDROID_X_OPTION_NAME = "kripton.androidx";
	public static String LOG_ENABLED_OPTION_NAME = "kripton.log";

	public static String schemaLocationDirectory;
	public static boolean androidX;

	public static String getSchemaLocation() {
		return schemaLocationDirectory;
	}

	public static void init(KriptonProcessor kriptonProcessor, ProcessingEnvironment processingEnv) {		
		// try to get config parameter from system environment		
		
		// DEBUG MODE
		// if it is already in debug mode, we keep it
		KriptonProcessor.DEBUG_MODE = KriptonProcessor.DEBUG_MODE
				|| "true".equals(getOptionValue(processingEnv, KriptonOptions.DEBUG_OPTION_NAME));				
		
		// LOG MODE
		// get kripton.log value
		KriptonProcessor.LOG_GENERATION_ENABLED_MODE = KriptonProcessor.LOG_GENERATION_ENABLED_MODE && !"false".equals(getOptionValue(processingEnv, KriptonOptions.LOG_ENABLED_OPTION_NAME));

		// SCHEMA MODE
		schemaLocationDirectory = getOptionValue(processingEnv, KriptonOptions.SCHEMA_LOCATION_OPTION_NAME) ;
		if (!StringUtils.hasText(schemaLocationDirectory)) {
			schemaLocationDirectory = "schemas";
		}

		// ANDROIDX MODE
		KriptonLiveDataManager.init(getOptionValue(processingEnv, KriptonOptions.ANDROID_X_OPTION_NAME));

		if (KriptonProcessor.DEBUG_MODE && !KriptonProcessor.JUNIT_TEST_MODE) {
			KriptonProcessor.info("Kripton Persistence Library v. " + Version.getVersion());
			
			{
				String value = getOptionValue(processingEnv, KriptonOptions.ANDROID_X_OPTION_NAME);
				if (StringUtils.hasText(value)) {
					KriptonProcessor.info("param " + KriptonOptions.ANDROID_X_OPTION_NAME + " = " + value+ " "+getOptionOrigin(processingEnv, KriptonOptions.ANDROID_X_OPTION_NAME));
				} else {
					KriptonProcessor.info("param " + KriptonOptions.ANDROID_X_OPTION_NAME + " = <unset>");
				}

				KriptonProcessor.info("\tjetpack live data support is " + (KriptonLiveDataManager.getInstance().isAndroidxSupport()
								? "enabled" : "disabled"));
			}
			
			{
				String value = getOptionValue(processingEnv, KriptonOptions.DEBUG_OPTION_NAME);
				if (StringUtils.hasText(value)) {
					KriptonProcessor.info("param " + KriptonOptions.DEBUG_OPTION_NAME + " = " + value+ " "+getOptionOrigin(processingEnv, KriptonOptions.DEBUG_OPTION_NAME));
				} else {
					KriptonProcessor.info("param " + KriptonOptions.DEBUG_OPTION_NAME + " = <unset>");
				}		
				KriptonProcessor.info("\tdebug mode is " + (KriptonProcessor.DEBUG_MODE
						? "enabled" : "disabled"));

			}
			
			{
				String value = getOptionValue(processingEnv, KriptonOptions.LOG_ENABLED_OPTION_NAME);
				if (StringUtils.hasText(value)) {
					KriptonProcessor.info("param " + KriptonOptions.LOG_ENABLED_OPTION_NAME + " = " + value+ " "+getOptionOrigin(processingEnv, KriptonOptions.LOG_ENABLED_OPTION_NAME));
				} else {
					KriptonProcessor.info("param " + KriptonOptions.LOG_ENABLED_OPTION_NAME + " = <unset>");
				}			
				KriptonProcessor.info("\tlog generation is " + (KriptonProcessor.LOG_GENERATION_ENABLED_MODE
								? "enabled" : "disabled"));

			}
			
			
			{
				String value = getOptionValue(processingEnv, KriptonOptions.SCHEMA_LOCATION_OPTION_NAME);
				if (StringUtils.hasText(value)) {
					KriptonProcessor.info("param " + KriptonOptions.SCHEMA_LOCATION_OPTION_NAME + " = " + value+ " "+getOptionOrigin(processingEnv, KriptonOptions.SCHEMA_LOCATION_OPTION_NAME));
				} else {
					KriptonProcessor.info("param " + KriptonOptions.SCHEMA_LOCATION_OPTION_NAME + " = <unset>");
				}
				KriptonProcessor
						.info("\tschemas location is '" + new File(schemaLocationDirectory).getAbsoluteFile() + "'");

			}
		}

	}
	
	private static String getOptionOrigin(ProcessingEnvironment processingEnv, String name) {
		{
			String value=processingEnv.getOptions().get(name);	
			if (StringUtils.hasText(value)) {
				return " from annotation processor configuration ("+value+")";
			}
		}
		
		{
			String value=System.getProperty(name);	
			if (StringUtils.hasText(value)) {
				return " from system property ("+value+")";
			}
		}
		
		{
			String value=System.getenv(name);	
			if (StringUtils.hasText(value)) {
				return " from system environment ("+value+")";
			}
		}
		
		return " from default value";
		
	}
	
	private static String getOptionValue(ProcessingEnvironment processingEnv, String name) {
		String result=System.getenv(name);
		
		if (StringUtils.hasText(System.getProperty(name))) {
			result=System.getProperty(name);
		}
		
		if (StringUtils.hasText(processingEnv.getOptions().get(name))) {
			result=processingEnv.getOptions().get(name);
		}
		
		return result;
	}

	
}
