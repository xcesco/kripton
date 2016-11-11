package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class DoubleConverter implements TypeConverter<Double> {

	@Override
	public Double parse(JsonParser jsonParser, boolean onlyText) throws IOException {
		if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (onlyText){
			return Double.valueOf(jsonParser.getText());
		} else {
			return jsonParser.getDoubleValue();
		}
	}

	@Override
	public void serialize(Double value, String fieldName, boolean writeFieldNameForObject, JsonGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeFieldName(fieldName);
		jsonGenerator.writeNumber(value);
	}

}
