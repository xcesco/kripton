package kripton70.typeconverters;

import java.io.IOException;

import kripton70.core.BinderGenerator;
import kripton70.core.BinderParser;

public class StringConverter implements TypeConverter<String>  {

	@Override
	public String parse(BinderParser parser) throws IOException {
		return parser.getText();
	}

	@Override
	public void serialize(String value, String fieldName, boolean writeFieldNameForObject, BinderGenerator generator) throws IOException {
		generator.writeFieldName(fieldName);
		generator.writeString(value);		
		
	}
}
