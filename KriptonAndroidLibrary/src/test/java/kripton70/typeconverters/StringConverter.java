package kripton70.typeconverters;

import java.io.IOException;

import kripton70.core.BinderSerializer;
import kripton70.persistence.JacksonParser;

public class StringConverter implements TypeConverter<String> {

	@Override
	public String parse(JacksonParser parser) {
		return parser.getText();
	}

	@Override
	public void serialize(BinderSerializer generator, boolean writeFieldNameForObject, String fieldName, String value) {
		if (writeFieldNameForObject)
			generator.writeFieldName(fieldName);
		generator.writeString(value);
	}
}
