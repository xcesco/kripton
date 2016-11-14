package kripton70.core;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Binder2YamlImpl extends AbstractJacksonBinder {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.XML;
	}
	
	@Override
	public JsonFactory createInnerFactory()
	{
		return new YAMLFactory();
	}
	
}
