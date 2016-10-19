package com.abubusoft.kripton.processor.sharedprefs.model;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.android.sharedprefs.PreferenceType;
import com.abubusoft.kripton.processor.core.ModelProperty;

public class PrefProperty extends ModelProperty {

	public PrefProperty(Element element) {
		super(element);
	}
	
	/**
	 * kind of preference associated
	 */
	protected PreferenceType preferenceType;

	public PreferenceType getPreferenceType() {
		return preferenceType;
	}

	public void setPreferenceType(PreferenceType preferenceType) {
		this.preferenceType = preferenceType;
	}



}
