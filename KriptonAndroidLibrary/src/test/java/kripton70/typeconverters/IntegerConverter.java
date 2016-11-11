package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class IntegerConverter implements TypeConverter<Integer> {

	@Override
	public Integer parse(JsonParser jsonParser, boolean onlyText) throws IOException {
		if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (onlyText){
			return Integer.valueOf(jsonParser.getText());
		} else {
			return jsonParser.getIntValue();
		}
	}

	@Override
	public void serialize(Integer value, String fieldName, boolean writeFieldNameForObject, JsonGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeFieldName(fieldName);
		jsonGenerator.writeNumber(value);
	}

}
