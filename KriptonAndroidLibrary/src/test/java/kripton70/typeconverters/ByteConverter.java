package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ByteConverter implements TypeConverter<Byte> {

	@Override
	public Byte parse(JsonParser jsonParser, boolean onlyText) throws IOException {
		if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (onlyText){
			return Byte.valueOf(jsonParser.getText());
		} else {
			return jsonParser.getByteValue();
			
		}
	}

	@Override
	public void serialize(Byte value, String fieldName, boolean writeFieldNameForObject, JsonGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeFieldName(fieldName);
		jsonGenerator.writeNumber(value);
	}

}
