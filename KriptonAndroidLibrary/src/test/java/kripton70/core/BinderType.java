package kripton70.core;

public enum BinderType {
	JSON(false),
	PROPERTIES(false),
	XML(false),
	YAML(false);	
	
	private BinderType(boolean value)
	{
		onlyText=value;
	}
	
	public final boolean onlyText; 
}
