package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderGenerator;
import kripton70.core.BinderParser;

public class DoubleConverter implements TypeConverter<Double> {

	@Override
	public Double parse(BinderParser parser, boolean onlyText) throws IOException {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (onlyText){
			return Double.valueOf(parser.getText());
		} else {
			return parser.getDoubleValue();
		}
	}

	@Override
	public void serialize(Double value, String fieldName, boolean writeFieldNameForObject, BinderGenerator generator) throws IOException {
		generator.writeFieldName(fieldName);
		generator.writeNumber(value);
		
	}

}
