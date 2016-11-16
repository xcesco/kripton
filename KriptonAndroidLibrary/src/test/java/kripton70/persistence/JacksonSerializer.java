package kripton70.persistence;

import java.io.IOException;

import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;

import kripton70.contexts.BinderContext;
import kripton70.contexts.BinderSerializer;
import kripton70.core.BinderType;

public class JacksonSerializer implements BinderSerializer, Visitable  {
	protected BinderContext context;

	JsonGenerator jacksonSerializer;

	public boolean onlyText;
	
	protected XMLStreamWriter2 xmlSerializer;

	public JacksonSerializer(BinderContext context, JsonGenerator jacksonSerializer, BinderType supportedFormat) {
		this.jacksonSerializer=jacksonSerializer;
		this.onlyText=supportedFormat.onlyText;
		this.context=context;
	}

	
	public void close() {
		try {
			jacksonSerializer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public boolean isOnlyText() {
		return onlyText;
	}

	public void writeAttribute(String name, boolean writeFieldNameForObject, long value) {
		// TODO Auto-generated method stub
		
	}

	public void writeAttribute(String fieldName, String value) {
		throw new KriptonRuntimeException("Not supported");
		
	}

	public void writeBoolean(Boolean value) {
		try {
			jacksonSerializer.writeBoolean(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public void writeEndArray() {
		try {
			jacksonSerializer.writeEndArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeEndObject() {
		try {
			jacksonSerializer.writeEndObject();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeFieldName(String fieldName) {
		try {
			jacksonSerializer.writeFieldName(fieldName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}			
	}

	public void writeNull() {
		try {
			jacksonSerializer.writeNull();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeNull(String fieldName) {
		throw new KriptonRuntimeException("Not supported");
		
	}

	public void writeNumber(Byte value) {
		try {
			jacksonSerializer.writeNumber(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}			
	}

	public void writeNumber(Double value) {
		try {
			jacksonSerializer.writeNumber(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeNumber(Float value) {
		try {
			jacksonSerializer.writeNumber(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeNumber(Integer value) {
		try {
			jacksonSerializer.writeNumber(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeNumber(Long value) {
		try {
			jacksonSerializer.writeNumber(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeNumber(Short value) {
		try {
			jacksonSerializer.writeNumber(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public void writeStartArray() {
		try {
			jacksonSerializer.writeStartArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeStartArray(String fieldName) {
		throw new KriptonRuntimeException("Not supported");
		
	}

	public void writeStartObject() {
		try {
			jacksonSerializer.writeStartObject();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeStartObject(String fieldName) {
		throw new KriptonRuntimeException("Not supported");		
	}

	public void writeString(String value) {
		try {
			jacksonSerializer.writeString(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}


	@Override
	public <E> void accept(BinderContext context, E bean, Visitor<E> visitor, boolean writeStartAndEnd) {
		visitor.visit(context, bean, this, writeStartAndEnd, context.getSupportedFormat().onlyText);
	}





}
