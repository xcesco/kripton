package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderGenerator;
import kripton70.core.BinderParser;

public class FloatConverter implements TypeConverter<Float> {

	@Override
	public Float parse(BinderParser parser, boolean onlyText) throws IOException {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (onlyText){
			return Float.valueOf(parser.getText());
		} else {
			return parser.getFloatValue();
		}
	}

	@Override
	public void serialize(Float value, String fieldName, boolean writeFieldNameForObject, BinderGenerator generator) throws IOException {
		generator.writeFieldName(fieldName);
		generator.writeNumber(value);
	}

}
