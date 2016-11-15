package kripton70.persistence;

import java.io.IOException;

import org.codehaus.stax2.XMLStreamWriter2;

import com.fasterxml.jackson.core.JsonGenerator;

import kripton70.contexts.BinderContext;
import kripton70.core.BinderSerializer;
import kripton70.core.BinderType;

public class JacksonSerializer implements BinderSerializer  {
	protected BinderContext context;

	JsonGenerator jacksonSerializer;

	public boolean onlyText;
	
	protected XMLStreamWriter2 xmlSerializer;

	public JacksonSerializer(BinderContext context, JsonGenerator jacksonSerializer, BinderType supportedFormat) {
		this.jacksonSerializer=jacksonSerializer;
		this.onlyText=supportedFormat.onlyText;
		this.context=context;
	}

	public void close() throws IOException {
		jacksonSerializer.close();
		
	}

	public boolean isOnlyText() {
		return onlyText;
	}

	public void writeBoolean(Boolean value) throws IOException {
		jacksonSerializer.writeBoolean(value);
	}

	public void writeEndArray() throws IOException {
		jacksonSerializer.writeEndArray();
		
	}

	public void writeEndObject() throws IOException {
		jacksonSerializer.writeEndObject();
		
	}

	public void writeFieldName(String fieldName) throws IOException {
		jacksonSerializer.writeFieldName(fieldName);			
	}

	public void writeNull() throws IOException {
		jacksonSerializer.writeNull();
		
	}

	public void writeNumber(Byte value) throws IOException {
		jacksonSerializer.writeNumber(value);				
	}

	public void writeNumber(Double value) throws IOException {
		jacksonSerializer.writeNumber(value);
		
	}

	public void writeNumber(Float value) throws IOException {
		jacksonSerializer.writeNumber(value);
		
	}

	public void writeNumber(Integer value) throws IOException {
		jacksonSerializer.writeNumber(value);
		
	}

	public void writeNumber(Long value) throws IOException {
		jacksonSerializer.writeNumber(value);
		
	}

	public void writeNumber(Short value) throws IOException {
		jacksonSerializer.writeNumber(value);
	}

	public void writeStartArray() throws IOException {
		jacksonSerializer.writeStartArray();
		
	}

	public void writeStartObject() throws IOException {
		jacksonSerializer.writeStartObject();
		
	}

	public void writeString(String value) throws IOException {
		jacksonSerializer.writeString(value);
		
	}
}
