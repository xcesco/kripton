package kripton70.persistence;

import java.io.IOException;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;

import kripton70.contexts.BinderContext;
import kripton70.core.BinderType;

public class JacksonWrapperSerializer implements BinderListSerializer {
	protected BinderContext<?, ?> context;

	public JsonGenerator jacksonGenerator;

	public JacksonWrapperSerializer(JsonGenerator jacksonSerializer, BinderType supportedFormat) {
		this.jacksonGenerator = jacksonSerializer;
	}

	@Override
	public void close() {
		try {
			jacksonGenerator.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e));
		}
		
	}
}
