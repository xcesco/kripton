package kripton70.persistence;

import com.fasterxml.jackson.core.JsonParser;

import kripton70.core.BinderType;

public class JacksonWrapperParser implements BinderListParser {

	public JsonParser jacksonParser;

	public JacksonWrapperParser(JsonParser parser, BinderType supportedFormat) {
		this.jacksonParser = parser;
	}

}
