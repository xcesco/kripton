package kripton70.persistence;

import java.io.IOException;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import kripton70.contexts.BinderContext;
import kripton70.core.BinderType;

public class JacksonParser implements BinderListParser {

	public JsonParser jacksonParser;

	public boolean onlyText;

	public JacksonParser(JsonParser parser, BinderType supportedFormat) {
		this.jacksonParser = parser;
		this.onlyText = supportedFormat.onlyText;
	}

	public Boolean getBooleanValue() {
		try {
			return jacksonParser.getBooleanValue();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Byte getByteValue() {
		try {
			return jacksonParser.getByteValue();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public String getCurrentName() {
		try {
			return jacksonParser.getCurrentName();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public JsonToken getCurrentToken() {
		return jacksonParser.getCurrentToken();
	}

	public Double getDoubleValue() {
		try {
			return jacksonParser.getDoubleValue();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Float getFloatValue() {
		try {
			return jacksonParser.getFloatValue();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Integer getIntValue() {
		try {
			return jacksonParser.getIntValue();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Long getLongValue() {
		try {
			return jacksonParser.getLongValue();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Short getShortValue() {
		try {
			return jacksonParser.getShortValue();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public String getText() {
		try {
			return jacksonParser.getText();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public boolean isOnlyText() {
		return onlyText;
	}

	public JsonToken nextToken() {
		try {
			return jacksonParser.nextToken();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public void skipChildren() {
		try {
			jacksonParser.skipChildren();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public String getString() {
		try {
			return jacksonParser.getText();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

}
