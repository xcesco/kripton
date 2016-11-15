package kripton70.core;

import java.io.IOException;

public interface BinderSerializer {

	void close() throws IOException;

	boolean isOnlyText();

	void writeBoolean(Boolean value) throws IOException;

	void writeEndArray() throws IOException;

	void writeEndObject() throws IOException;

	void writeFieldName(String fieldName) throws IOException;

	void writeNull() throws IOException;

	void writeNumber(Byte value) throws IOException;

	void writeNumber(Double value) throws IOException;

	void writeNumber(Float value) throws IOException;

	void writeNumber(Integer value) throws IOException;

	void writeNumber(Long value) throws IOException;

	void writeNumber(Short value) throws IOException;

	void writeStartArray() throws IOException;

	void writeStartObject() throws IOException;

	void writeString(String value) throws IOException;
}
