package kripton70.core;


public interface BinderSerializer {

	void close();
	
	boolean isOnlyText();

	void writeAttribute(String name, boolean writeFieldNameForObject, long value);

	void writeAttribute(String fieldName, String value);

	void writeBoolean(Boolean value);

	void writeEndArray();

	void writeEndObject();

	void writeFieldName(String fieldName);

	void writeNull();

	void writeNull(String fieldName);

	void writeNumber(Byte value);

	void writeNumber(Double value);

	void writeNumber(Float value);

	void writeNumber(Integer value);

	void writeNumber(Long value);

	void writeNumber(Short value);

	void writeStartArray();

	void writeStartArray(String fieldName);

	void writeStartObject();

	void writeStartObject(String fieldName);

	void writeString(String value);
}
