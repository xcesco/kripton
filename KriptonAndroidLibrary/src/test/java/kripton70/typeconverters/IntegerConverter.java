package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderGenerator;
import kripton70.core.BinderParser;

public class IntegerConverter implements TypeConverter<Integer> {

	@Override
	public Integer parse(BinderParser parser, boolean onlyText) throws IOException {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (onlyText){
			return Integer.valueOf(parser.getText());
		} else {
			return parser.getIntValue();
		}
	}

	@Override
	public void serialize(Integer value, String fieldName, boolean writeFieldNameForObject, BinderGenerator generator) throws IOException {
		generator.writeFieldName(fieldName);
		generator.writeNumber(value);
		
	}

}
