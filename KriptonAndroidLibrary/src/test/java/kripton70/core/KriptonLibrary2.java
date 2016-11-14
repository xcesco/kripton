package kripton70.core;

import java.util.HashMap;
import java.util.Map;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

import kripton70.contexts.BinderJsonContext;

public abstract class KriptonLibrary2 {
	
	public static void registryBinder(BinderContext factory)
	{
		binders.put(factory.getSupportedFormat(), factory);
	}
	
	private static final Map<BinderType, BinderContext> binders=new HashMap<>();
	
	static {
		registryBinder(new BinderJsonContext());
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
	

	public static BinderContext getBinder(BinderType format) {
		BinderContext binder=binders.get(format);
		
		if (binder==null) throw new KriptonRuntimeException(String.format("%s format is not supported", format));
		
		return binder;
	}

	/*public static <T> String serialize(BinderType type, T bean) throws IOException {
		return getBinder(type).serialize(bean);
	}

	public static Bean parse(BinderType type, String output, Class<Bean> clazz) throws IOException {
		return getBinder(type).parse(output,clazz);
	}*/

}
