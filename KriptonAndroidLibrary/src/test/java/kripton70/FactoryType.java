package kripton70;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public enum FactoryType {
	JSON(new JsonFactory(), false),
	PROPERTIES(new JavaPropsFactory(), true),
	XML(null, false),
	YAML(new YAMLFactory(), false);
	
	private FactoryType(JsonFactory factory, boolean onlyText)
	{
		this.factory=factory;
		this.onlyText=onlyText;
	}
	
	public final boolean onlyText;
	
	public final JsonFactory factory;
}
