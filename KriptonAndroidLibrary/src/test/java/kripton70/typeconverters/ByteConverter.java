package kripton70.typeconverters;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderSerializer;
import kripton70.persistence.JacksonParser;

public class ByteConverter implements TypeConverter<Byte> {

	@Override
	public Byte parse(JacksonParser parser) {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (parser.onlyText) {
			return Byte.valueOf(parser.getText());
		} else {
			return parser.getByteValue();

		}
	}

	@Override
	public void serialize(BinderSerializer generator, boolean writeFieldNameForObject, String fieldName, Byte value) {
		if (writeFieldNameForObject)
			generator.writeFieldName(fieldName);
		generator.writeNumber(value);
	}

}
