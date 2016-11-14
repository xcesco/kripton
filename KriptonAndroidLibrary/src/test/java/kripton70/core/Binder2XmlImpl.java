package kripton70.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Binder2XmlImpl implements Binder2 {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.XML;
	}
	
	@Override
	public <E> E parse(InputStream is, Class<E> jsonObjectClass) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E parse(String jsonString, Class<E> jsonObjectClass) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E parse(InputStream is, ParameterizedType<E> jsonObjectType) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E parse(String jsonString, ParameterizedType<E> jsonObjectType) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> parseList(InputStream is, Class<E> jsonObjectClass) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> parseList(String jsonString, Class<E> jsonObjectClass) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> String serialize(E object) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> void serialize(E object, OutputStream os) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E> String serialize(E object, ParameterizedType<E> parameterizedType) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> void serialize(E object, ParameterizedType<E> parameterizedType, OutputStream os) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E> String serialize(List<E> list, Class<E> jsonObjectClass) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> void serialize(List<E> list, OutputStream os, Class<E> jsonObjectClass) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}
