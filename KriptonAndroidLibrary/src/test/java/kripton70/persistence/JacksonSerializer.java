package kripton70.persistence;

import java.io.IOException;

import kripton70.contexts.BinderContext;
import kripton70.core.BinderType;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;

public class JacksonSerializer implements BinderListSerializer {
	protected BinderContext<?, ?> context;

	JsonGenerator jacksonSerializer;

	public JacksonSerializer(JsonGenerator jacksonSerializer, BinderType supportedFormat) {
		this.jacksonSerializer = jacksonSerializer;
	}

	public void close() {
		try {
			jacksonSerializer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

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

	public void writeFieldName(String fieldName) throws IOException {
		jacksonSerializer.writeFieldName(fieldName);
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

	public void writeString(String value) {
		try {
			jacksonSerializer.writeString(value);
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

}
