package com.abubusoft.kripton.isocodes.output;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Country {

	public long area;

	public String callingCode;

	public String region;

	public String name;
	
	public String code;

	public Map<Translation, String> translatedName = new LinkedHashMap<Translation, String>();

	public boolean hasTranslationFor(Translation type) {
		return translatedName.containsKey(type);
	}

	public String getTranslatedName(String language) {
		Translation t = Translation.valueOf(language.toUpperCase());

		if (t != null)
			return translatedName.get(t);

		return name;
	}
}
