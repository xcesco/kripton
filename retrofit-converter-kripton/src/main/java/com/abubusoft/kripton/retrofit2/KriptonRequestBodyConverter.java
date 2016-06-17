package com.abubusoft.kripton.retrofit2;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

final class KriptonRequestBodyConverter<T> implements Converter<T, RequestBody> {
	private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	BinderWriter writer;
	BinderReader reader;
	private Class<T> clazz;

	KriptonRequestBodyConverter(Class<T> clazz, BinderWriter writer, BinderReader reader) {
		this.writer = writer;
		this.reader = reader;
		this.clazz = clazz;
	}

	@Override
	public RequestBody convert(T value) throws IOException {
		Buffer buffer = new Buffer();	
		
		try {
			writer.write(value, buffer.outputStream());
			return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
		} catch (WriterException | MappingException e) {			
			e.printStackTrace();
			return null;
		} finally
		{
			buffer.close();
		}
		
		
	}
}