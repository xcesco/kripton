package com.abubusoft.kripton.binder2.persistence;

import com.abubusoft.kripton.binder2.BinderType;
import com.fasterxml.jackson.core.JsonParser;

public class JacksonWrapperParser implements BinderListParser {

	public JsonParser jacksonParser;

	public JacksonWrapperParser(JsonParser parser, BinderType supportedFormat) {
		this.jacksonParser = parser;
	}

}
