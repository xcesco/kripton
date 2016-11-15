package kripton70.contexts;

import com.fasterxml.jackson.core.JsonFactory;

import kripton70.core.BinderType;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class JsonBinderContext extends AbstractJacksonBinderContext {

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
