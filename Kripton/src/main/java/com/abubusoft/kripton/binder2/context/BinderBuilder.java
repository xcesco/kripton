package com.abubusoft.kripton.binder2.context;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
import com.abubusoft.kripton.binder2.persistence.SerializerWrapper;
import com.fasterxml.jackson.core.JsonEncoding;

public interface BinderBuilder {
	
	ParserWrapper createParser(byte[] data);

	ParserWrapper createParser(File file);

	ParserWrapper createParser(InputStream in);

	ParserWrapper createParser(Reader reader);

	ParserWrapper createParser(String content);

	SerializerWrapper createSerializer(File file);

	SerializerWrapper createSerializer(File file, JsonEncoding encoding);

	SerializerWrapper createSerializer(OutputStream out);

	SerializerWrapper createSerializer(OutputStream out, JsonEncoding encoding);

	SerializerWrapper createSerializer(Writer writer);
}
