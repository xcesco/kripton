package kripton70.core;

import java.io.IOException;

import org.codehaus.stax2.XMLStreamReader2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import kripton70.contexts.BinderContext;

public class BinderParser {

	protected BinderContext context;

	public JsonParser jacksonParser;
	
	public boolean onlyText;
	
	protected XMLStreamReader2 xmlParser;

	public BinderParser(BinderContext context, JsonParser parser, BinderType supportedFormat)
	{
		this.jacksonParser=parser;
		this.onlyText=supportedFormat.onlyText;
	}
	
	public BinderParser(BinderContext context, XMLStreamReader2 xmlStreamReader, BinderType supportedFormat) {
		this.context=context;
		this.onlyText=supportedFormat.onlyText;
		this.xmlParser=xmlStreamReader;
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

	public boolean isOnlyText() {
		return onlyText;
	}

	public JsonToken nextToken() throws IOException {
		return jacksonParser.nextToken();		
	}

	public void skipChildren() throws IOException {
		jacksonParser.skipChildren();		
	}
}
