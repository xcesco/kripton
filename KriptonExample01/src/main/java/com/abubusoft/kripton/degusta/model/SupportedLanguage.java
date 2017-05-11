package com.abubusoft.kripton.degusta.model;

import java.util.Locale;

public enum SupportedLanguage {
	ENGLISH(Locale.ENGLISH),
	FRENCH(Locale.FRENCH),
	ITALIAN(Locale.ITALIAN);
	
	private Locale value;

	private SupportedLanguage(Locale locale)
	{
		this.value=locale;
	}
	
	public Locale getValue()
	{
		return value;
	}
}
