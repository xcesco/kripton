package com.abubusoft.kripton.binder.persistence;

import java.io.IOException;

import com.abubusoft.kripton.binder.BinderType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;

public class JacksonWrapperSerializer implements SerializerWrapper {
	public JsonGenerator jacksonGenerator;

	public JacksonWrapperSerializer(JsonGenerator jacksonSerializer, BinderType supportedFormat) {
		this.jacksonGenerator = jacksonSerializer;
	}

	@Override
	public void close() {
		try {
			if (!jacksonGenerator.isClosed())
				jacksonGenerator.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e));
		}
		
	}
}
