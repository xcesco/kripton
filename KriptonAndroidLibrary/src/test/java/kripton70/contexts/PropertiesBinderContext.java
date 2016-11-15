package kripton70.contexts;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;

import kripton70.core.BinderType;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class PropertiesBinderContext extends AbstractJacksonBinderContext {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.PROPERTIES;
	}
	
	@Override
	public JsonFactory createInnerFactory()
	{
		return new JavaPropsFactory();
	}
	
}
