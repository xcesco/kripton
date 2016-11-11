package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderGenerator;
import kripton70.core.BinderParser;

public class ShortConverter implements TypeConverter<Short> {

	@Override
	public Short parse(BinderParser parser, boolean onlyText) throws IOException {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (onlyText) {
			return Short.valueOf(parser.getText());
		} else {
			return parser.getShortValue();			
		}
	}

	@Override
	public void serialize(Short value, String fieldName, boolean writeFieldNameForObject, BinderGenerator generator) throws IOException {
		generator.writeFieldName(fieldName);
		generator.writeNumber(value);
		
	}

}
