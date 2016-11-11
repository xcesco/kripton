package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ShortConverter implements TypeConverter<Short> {

	@Override
	public Short parse(JsonParser jsonParser, boolean onlyText) throws IOException {
		if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (onlyText) {
			return Short.valueOf(jsonParser.getText());
		} else {
			return jsonParser.getShortValue();			
		}
	}

	@Override
	public void serialize(Short value, String fieldName, boolean writeFieldNameForObject, JsonGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeFieldName(fieldName);
		jsonGenerator.writeNumber(value);
	}

}
