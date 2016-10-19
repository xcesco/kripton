package com.abubusoft.kripton.processor.kripton42;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public interface BindMapper<E> {

	void write(JsonGenerator generator, E source) throws IOException;
	
	void writeXml(ToXmlGenerator generator, E source) throws IOException;
	
	E read();
}
