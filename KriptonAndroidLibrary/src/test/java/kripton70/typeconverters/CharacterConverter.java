package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class CharacterConverter implements TypeConverter<Character> {

	@Override
	public Character parse(JsonParser jsonParser) throws IOException {
		if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else {
			return jsonParser.getText().charAt(0);
		}
	}

	@Override
	public void serialize(Character object, String fieldName, boolean writeFieldNameForObject, JsonGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeFieldName(fieldName);
		jsonGenerator.writeString(String.valueOf(object));
	}

}
