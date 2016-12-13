package com.abubusoft.kripton.binder.persistence;

import java.io.IOException;

import com.abubusoft.kripton.binder.BinderType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonParser;

public class JacksonWrapperParser implements ParserWrapper {

	public JsonParser jacksonParser;

	public JacksonWrapperParser(JsonParser parser, BinderType supportedFormat) {
		this.jacksonParser = parser;
	}

	@Override
	public void close() {
		try {
			if (!jacksonParser.isClosed())
				jacksonParser.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e));
		}
		
	}

}
