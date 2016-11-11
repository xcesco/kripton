package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class StringConverter implements TypeConverter<String>  {

	@Override
	public String parse(JsonParser jsonParser, boolean onlyText) throws IOException {
		return jsonParser.getText();
	}

	@Override
	public void serialize(String value, String fieldName, boolean writeFieldNameForObject, JsonGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeFieldName(fieldName);
		jsonGenerator.writeString(value);		
	}
}
