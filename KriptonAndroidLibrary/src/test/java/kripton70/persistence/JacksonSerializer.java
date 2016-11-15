package kripton70.persistence;

import java.io.IOException;

import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;

import kripton70.contexts.BinderContext;
import kripton70.core.BinderSerializer;
import kripton70.core.BinderType;

public class JacksonSerializer implements BinderSerializer  {
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

	public void writeStartObject() {
		try {
			jacksonSerializer.writeStartObject();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}

	public void writeString(String value) {
		try {
			jacksonSerializer.writeString(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		
	}
}
