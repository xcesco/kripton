package kripton70.typeconverters;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderSerializer;
import kripton70.persistence.JacksonParser;

public class CharacterConverter implements TypeConverter<Character> {
	@Override
	public Character parse(JacksonParser parser) {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else {
			return parser.getText().charAt(0);
		}
	}

	@Override
	public void serialize(BinderSerializer generator, boolean writeFieldNameForObject, String fieldName, Character value) {
		if (writeFieldNameForObject)
		generator.writeFieldName(fieldName);
		generator.writeString(String.valueOf(value));
		
	}

}
