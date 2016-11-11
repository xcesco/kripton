package kripton70.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;

public class BinderGenerator {
	
	public BinderGenerator(JsonGenerator generator, BinderType supportedFormat) {
		this.generator=generator;
		this.value=supportedFormat.onlyText;
	}

	public JsonGenerator generator;
	
	public boolean value;

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
}
