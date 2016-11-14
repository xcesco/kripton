package kripton70.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;

public class BinderGenerator {
	
	protected BinderContext context;

	public BinderGenerator(BinderContext context, JsonGenerator generator, BinderType supportedFormat) {
		this.generator=generator;
		this.onlyText=supportedFormat.onlyText;
		this.context=context;
	}

	public boolean isOnlyText() {
		return onlyText;
	}

	public JsonGenerator generator;
	
	public boolean onlyText;

	public void writeFieldName(String fieldName) throws IOException {
		generator.writeFieldName(fieldName);			
	}

	public void writeBoolean(Boolean value) throws IOException {
		generator.writeBoolean(value);
	}

	public void writeNumber(Byte value) throws IOException {
		generator.writeNumber(value);
		
	}

	public void writeString(String value) throws IOException {
		generator.writeString(value);
		
	}

	public void writeNumber(Double value) throws IOException {
		generator.writeNumber(value);
		
	}

	public void writeNumber(Float value) throws IOException {
		generator.writeNumber(value);
		
	}

	public void writeNumber(Integer value) throws IOException {
		generator.writeNumber(value);
		
	}

	public void writeNumber(Long value) throws IOException {
		generator.writeNumber(value);
		
	}

	public void writeNumber(Short value) throws IOException {
		generator.writeNumber(value);
	}

	public void close() throws IOException {
		generator.close();
		
	}

	public void writeStartArray() throws IOException {
		generator.writeStartArray();
		
	}

	public void writeNull() throws IOException {
		generator.writeNull();
		
	}

	public void writeEndArray() throws IOException {
		generator.writeEndArray();
		
	}

	public void writeStartObject() throws IOException {
		generator.writeStartObject();
		
	}

	public void writeEndObject() throws IOException {
		generator.writeEndObject();
		
	}
}
