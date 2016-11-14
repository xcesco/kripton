package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderGenerator;
import kripton70.core.BinderParser;

public class BooleanConverter implements TypeConverter<Boolean> {

	@Override
	public Boolean parse(BinderParser parser) throws IOException {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (parser.onlyText) {
			return Boolean.valueOf(parser.getText());
		} else {
			return parser.getBooleanValue();
		}
	}

	@Override
	public void serialize(BinderGenerator generator, boolean writeFieldNameForObject, String fieldName, Boolean value) throws IOException {
		generator.writeFieldName(fieldName);
		generator.writeBoolean(value);		
	}

}
