package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderSerializer;
import kripton70.core.BinderParser;

public class CharacterConverter implements TypeConverter<Character> {
	@Override
	public Character parse(BinderParser parser) throws IOException {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else {
			return parser.getText().charAt(0);
		}
	}

	@Override
	public void serialize(BinderSerializer generator, boolean writeFieldNameForObject, String fieldName, Character value) throws IOException {
		generator.writeFieldName(fieldName);
		generator.writeString(String.valueOf(value));
		
	}

}
