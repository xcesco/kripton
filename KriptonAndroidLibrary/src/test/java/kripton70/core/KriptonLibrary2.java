package kripton70.core;

import java.util.HashMap;
import java.util.Map;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

public class KriptonLibrary2 {
	
	public void registryBinder(Binder2 factory)
	{
		binders.put(factory.getSupportedFormat(), factory);
	}
	
	private static final Map<BinderType, Binder2> binders=new HashMap<>();
	
	{
		registryBinder(new Binder2JsonImpl());
	}
	
//	private static final ListMapper LIST_MAPPER = new ListMapper();
//    private static final MapMapper MAP_MAPPER = new MapMapper();

 
/*
    private static final ConcurrentHashMap<ParameterizedType, JsonMapper> PARAMETERIZED_OBJECT_MAPPERS = new ConcurrentHashMap<ParameterizedType, JsonMapper>();

    private static final SimpleArrayMap<Class, TypeConverter> TYPE_CONVERTERS = new SimpleArrayMap<>();
    static {
        registerTypeConverter(Date.class, new DefaultDateConverter());
        registerTypeConverter(Calendar.class, new DefaultCalendarConverter());
    }*/

	/**
	 * The JsonFactory that should be used throughout the entire app.
	 * */
	//public static final JsonFactory JSON_FACTORY = /*new JavaPropsFactory();*/ new YAMLFactory();//new JsonFactory();
	
	//public static final JsonFactory JSON_FACTORY = new JsonFactory();
	

	public static Binder2 getBinder(BinderType format) {
		Binder2 binder=binders.get(format);
		
		if (binder==null) throw new KriptonRuntimeException(String.format("%s format is not supported", format));
		
		return binder;
	}

}
