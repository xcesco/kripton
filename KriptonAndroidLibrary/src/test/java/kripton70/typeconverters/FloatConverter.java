package kripton70.typeconverters;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderSerializer;
import kripton70.persistence.JacksonParser;

public class FloatConverter implements TypeConverter<Float> {

	@Override
	public Float parse(JacksonParser parser) {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (parser.onlyText){
			return Float.valueOf(parser.getText());
		} else {
			return parser.getFloatValue();
		}
	}

	@Override
	public void serialize(BinderSerializer generator, boolean writeFieldNameForObject, String fieldName, Float value) {
		if (writeFieldNameForObject)
		generator.writeFieldName(fieldName);
		generator.writeNumber(value);
	}

}
