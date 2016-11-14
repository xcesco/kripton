package kripton70.typeconverters;

import java.io.IOException;

import kripton70.core.BinderSerializer;
import kripton70.core.BinderParser;

import com.fasterxml.jackson.core.JsonToken;

public class IntegerConverter implements TypeConverter<Integer> {

	@Override
	public Integer parse(BinderParser parser) throws IOException {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (parser.onlyText){
			return Integer.valueOf(parser.getText());
		} else {
			return parser.getIntValue();
		}
	}

	@Override
	public void serialize(BinderSerializer generator, boolean writeFieldNameForObject, String fieldName, Integer value) throws IOException {
		if (writeFieldNameForObject)
		generator.writeFieldName(fieldName);
		generator.writeNumber(value);
		
	}

}
