package kripton70.typeconverters;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderSerializer;
import kripton70.persistence.JacksonParser;

public class IntegerConverter implements TypeConverter<Integer> {

	@Override
	public Integer parse(JacksonParser parser) {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (parser.onlyText){
			return Integer.valueOf(parser.getText());
		} else {
			return parser.getIntValue();
		}
	}

	@Override
	public void serialize(BinderSerializer generator, boolean writeFieldNameForObject, String fieldName, Integer value) {
		if (writeFieldNameForObject)
		generator.writeFieldName(fieldName);
		generator.writeNumber(value);
		
	}

}
