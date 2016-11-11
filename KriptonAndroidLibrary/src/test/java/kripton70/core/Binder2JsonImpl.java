package kripton70.core;

import com.fasterxml.jackson.core.JsonFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Binder2JsonImpl extends AbstractBinder {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.JSON;
	}
	
	@Override
	public JsonFactory createInnerFactory()
	{
		return new JsonFactory();
	}
	
}
