package kripton70.typeconverters;

import java.io.IOException;

import kripton70.core.BinderGenerator;
import kripton70.core.BinderParser;

/**
 * Implement this interface in order to create a way to custom parse and serialize @JsonFields
 **/
public interface TypeConverter<T> {

	/**
	 * Called to parse the current object in the jsonParser to an object of type T
	 *
	 * @param binderParser
	 *            The JsonParser that is pre-configured for this field.
	 * 
	 */
	T parse(BinderParser parser) throws IOException;

	/**
	 * Called to serialize an object of type T to JSON using the JsonGenerator and field name.
	 *
	 * @param value
	 *            The object to serialize
	 * @param fieldName
	 *            The JSON field name of the object when it is serialized
	 * @param writeFieldNameForObject
	 *            If true, you're responsible for calling jsonGenerator.writeFieldName(fieldName) before writing the field
	 * @param binderGenerator
	 *            The JsonGenerator object to which the object should be written
	 */
	void serialize(T value, String fieldName, boolean writeFieldNameForObject, BinderGenerator generator) throws IOException;

}