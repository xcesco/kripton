package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class BooleanConverter implements TypeConverter<Boolean> {

	@Override
	public Boolean parse(JsonParser jsonParser) throws IOException {
		if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else {
			return jsonParser.getBooleanValue();
		}
	}

	@Override
	public void serialize(Boolean value, String fieldName, boolean writeFieldNameForObject, JsonGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeFieldName(fieldName);
		jsonGenerator.writeBoolean(value);		
	}

}
