package kripton70.persistence;

import java.io.IOException;

import kripton70.core.BinderType;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JacksonParser implements BinderListParser {

	public JsonParser jacksonParser;

	public JacksonParser(JsonParser parser, BinderType supportedFormat) {
		this.jacksonParser = parser;
	}

	public Boolean getBooleanValue() throws IOException {
		return jacksonParser.getBooleanValue();
	}

	public Byte getByteValue() throws IOException {
		return jacksonParser.getByteValue();
	}

	public String getCurrentName() throws IOException {
		return jacksonParser.getCurrentName();
	}

	public JsonToken getCurrentToken() {
		return jacksonParser.getCurrentToken();
	}

	public Double getDoubleValue() throws IOException {
		return jacksonParser.getDoubleValue();
	}

	public Float getFloatValue() throws IOException {
			return jacksonParser.getFloatValue();
	}

	public Integer getIntValue() throws IOException {
		return jacksonParser.getIntValue();
	}

	public Long getLongValue() throws IOException {
		return jacksonParser.getLongValue();
	}

	public Short getShortValue() throws IOException {
		return jacksonParser.getShortValue();
	}

	public String getText() throws IOException {
		return jacksonParser.getText();
	}

	public JsonToken nextToken() throws IOException {
		return jacksonParser.nextToken();
	}

	public void skipChildren() throws IOException {
		jacksonParser.skipChildren();
	}

	public char getCharValue() throws IOException {
		return (char)jacksonParser.getIntValue();
	}

}
