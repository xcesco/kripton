package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderSerializer;
import kripton70.persistence.JacksonParser;

public class BooleanConverter implements TypeConverter<Boolean> {

	@Override
	public Boolean parse(JacksonParser parser) {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (parser.onlyText) {
			return Boolean.valueOf(parser.getText());
		} else {
			return parser.getBooleanValue();
		}
	}

	@Override
	public void serialize(BinderSerializer generator, boolean writeFieldNameForObject, String fieldName, Boolean value) {
		if (writeFieldNameForObject)
			generator.writeFieldName(fieldName);
		generator.writeBoolean(value);
	}

}
