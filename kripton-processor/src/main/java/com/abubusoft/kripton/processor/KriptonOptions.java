package com.abubusoft.kripton.processor;

import java.io.File;

import javax.annotation.processing.ProcessingEnvironment;

import com.abubusoft.kripton.common.StringUtils;

public abstract class KriptonOptions {
	public static String DEBUG = "kripton.debug";
	public static String SCHEMA_LOCATION_OPTIONS = "kripton.schemaLocation";
	public static String ANDROID_X_OPTIONS = "kripton.androidx";

	public static String schemaLocationDirectory;
	public static boolean androidX;

	public static String getSchemaLocation() {
		return schemaLocationDirectory;
	}

	public static void init(KriptonProcessor kriptonProcessor, ProcessingEnvironment processingEnv) {
		// if it is already in debug mode, we keep it
		KriptonProcessor.DEBUG_MODE = KriptonProcessor.DEBUG_MODE
				|| "true".equals(processingEnv.getOptions().get(KriptonOptions.DEBUG));

		schemaLocationDirectory = processingEnv.getOptions().get(KriptonOptions.SCHEMA_LOCATION_OPTIONS);
		if (!StringUtils.hasText(schemaLocationDirectory)) {
			schemaLocationDirectory = "schemas";
		}

		KriptonLiveDataManager.init(processingEnv.getOptions().get(KriptonOptions.ANDROID_X_OPTIONS));

		if (KriptonProcessor.DEBUG_MODE) {
			KriptonProcessor.info("Kripton Persistence Library v. " + Version.getVersion());
			{
				String value = processingEnv.getOptions().get(KriptonOptions.ANDROID_X_OPTIONS);
				if (StringUtils.hasText(value)) {
					KriptonProcessor.info("param " + KriptonOptions.ANDROID_X_OPTIONS + " = " + value);
				} else {
					KriptonProcessor.info("param " + KriptonOptions.ANDROID_X_OPTIONS + " = <unset>");
				}

				KriptonProcessor.info("\tjetpack live data support is " + (androidx.lifecycle.LiveData.class
						.equals(KriptonLiveDataManager.getInstance().getLiveDataClazz().getName().toString())
								? "enabled" : "disabled"));
			}
			{
				String value = processingEnv.getOptions().get(KriptonOptions.SCHEMA_LOCATION_OPTIONS);
				if (StringUtils.hasText(value)) {
					KriptonProcessor.info("param " + KriptonOptions.SCHEMA_LOCATION_OPTIONS + " = " + value);
				} else {
					KriptonProcessor.info("param " + KriptonOptions.SCHEMA_LOCATION_OPTIONS + " = <unset>");
				}
				KriptonProcessor
						.info("\tschemas location is '" + new File(schemaLocationDirectory).getAbsoluteFile() + "'");

			}
		}

	}
}
