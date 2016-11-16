package kripton70.core;

import java.io.IOException;

import org.codehaus.stax2.XMLStreamReader2;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import kripton70.contexts.BinderContext;

public class BinderParser {

	protected BinderContext context;

	public JsonParser jacksonParser;

	public boolean onlyText;

	protected XMLStreamReader2 xmlParser;

	public BinderParser(BinderContext context, JsonParser parser, BinderType supportedFormat) {
		this.jacksonParser = parser;
		this.onlyText = supportedFormat.onlyText;
	}

	public BinderParser(BinderContext context, XMLStreamReader2 xmlStreamReader, BinderType supportedFormat) {
		this.context = context;
		this.onlyText = supportedFormat.onlyText;
		this.xmlParser = xmlStreamReader;
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

	public JsonToken nextToken()  {
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
}
