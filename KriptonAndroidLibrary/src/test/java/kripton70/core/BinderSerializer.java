package kripton70.core;


public interface BinderSerializer {

	void close();

	boolean isOnlyText();

	void writeBoolean(Boolean value);

	void writeEndArray();

	void writeEndObject();

	void writeFieldName(String fieldName);

	void writeNull();

	void writeNumber(Byte value);

	void writeNumber(Double value);

	void writeNumber(Float value);

	void writeNumber(Integer value);

	void writeNumber(Long value);

	void writeNumber(Short value);

	void writeStartArray();

	void writeStartObject();

	void writeString(String value);
}
