package kripton70.typeconverters;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderSerializer;
import kripton70.persistence.JacksonParser;

public class DoubleConverter implements TypeConverter<Double> {

	@Override
	public Double parse(JacksonParser parser) {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (parser.onlyText){
			return Double.valueOf(parser.getText());
		} else {
			return parser.getDoubleValue();
		}
	}

	@Override
	public void serialize(BinderSerializer generator, boolean writeFieldNameForObject, String fieldName, Double value) {
		if (writeFieldNameForObject)
		generator.writeFieldName(fieldName);
		generator.writeNumber(value);
		
	}

}
