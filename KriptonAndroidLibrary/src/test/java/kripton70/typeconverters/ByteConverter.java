package kripton70.typeconverters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import kripton70.core.BinderGenerator;
import kripton70.core.BinderParser;

public class ByteConverter implements TypeConverter<Byte> {

	@Override
	public Byte parse(BinderParser parser, boolean onlyText) throws IOException {
		if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
			return null;
		} else if (onlyText){
			return Byte.valueOf(parser.getText());
		} else {
			return parser.getByteValue();
			
		}
	}

	@Override
	public void serialize(Byte value, String fieldName, boolean writeFieldNameForObject, BinderGenerator generator) throws IOException {
		generator.writeFieldName(fieldName);
		generator.writeNumber(value);
		
	}

	

}
