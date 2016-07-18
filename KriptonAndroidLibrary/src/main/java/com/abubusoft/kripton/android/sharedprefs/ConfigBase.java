package com.abubusoft.kripton.android.sharedprefs;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.abubusoft.kripton.android.Logger;

/**
 * <p>
 * Configurazione di base
 * </p>
 * 
 * @author Francesco Benincasa
 * 
 */
public abstract class ConfigBase implements Config {

	/**
	 * <p>
	 * Indica se contiene degli elementi che derivano dal packmanager.
	 * </p>
	 * 
	 * @return
	 */
	public static boolean hashPackManagerDependencies(String clazzName) {
		return false;
	}


	/* (non-Javadoc)
	 * @see org.abubu.elio.config.Config#readPreferences(android.content.Context)
	 */
	@Override
	public void readPreferences(Context context) {
	//	SharedPreferences preference=context.getSharedPreferences(ApplicationManager.DEFAULT_SHARED_PREFERENCES_NAME, 0);
		
		//readPreferences(preference);
		
	}

	/* (non-Javadoc)
	 * @see org.abubu.elio.config.Config#writePreferences(android.content.Context)
	 */
	@Override
	public void writePreferences(Context context) {
	//	SharedPreferences preference=context.getSharedPreferences(ApplicationManager.DEFAULT_SHARED_PREFERENCES_NAME, 0);
		
		//writePreferences(preference);
	}

	/**
	 * package name associato alla configurazione
	 */
	@PreferenceConfig
	public String packageName; //= ApplicationManager.instance().info.packageName;

	/**
	 * versione dell'applicazione
	 */
	@PreferenceConfig
	public String packageVersion;// = ApplicationManager.instance().info.version.toString();

	/**
	 * <p>
	 * Locale, serve per la lingua.
	 * </p>
	 */
	@PreferenceConfig(copyOnReset = false)
	public String locale; //= LocaleUtility.getSupportedDefaultLanguage();

	public static final String DEFAULT_KEY = "";

	/**
	 * <p>
	 * Separatore degli elementi di un array inserito in una stringa
	 * </p>
	 */
	public static final String STRING_ARRAY_SEPARATOR = ";##@@;";

	/**
	 * <p>
	 * Annotated fields
	 * </p>
	 */
	private Map<String, Field> annotatedFields;

	/**
	 * <p>
	 * </p>
	 */
	public ConfigBase() {
		annotatedFields = new HashMap<String, Field>();
		Field[] fields = getClass().getFields();

		// mettiamo in annotatedFields tutti i campi annotation
		PreferenceConfig annotation;
		for (Field field : fields) {
			annotation = field.getAnnotation(PreferenceConfig.class);
			if (annotation != null) {
				annotatedFields.put(field.getName(), field);
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public void reset() {
		try {
			Object newConfig = getClass().newInstance();
			Field[] fields = getClass().getFields();

			// mettiamo in annotatedFields tutti i campi annotati
			PreferenceConfig annotation;
			for (Field item : fields) {
				annotation = item.getAnnotation(PreferenceConfig.class);
				if (annotation != null) {

					if (annotation.copyOnReset()) {
						item.set(this, item.get(newConfig));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.fatal("Impossible to clone config!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.abubu.neon.config.Config#readPreferences(android.content. SharedPreferences)
	 */
	public void readPreferences(SharedPreferences sharedPreference) {
		readPreference("", sharedPreference);
	}
	
	public void readPreference(Context context, String configKey) {
		//SharedPreferences preference=context.getSharedPreferences(ApplicationManager.DEFAULT_SHARED_PREFERENCES_NAME, 0);
		
		//readPreference(configKey, preference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.abubu.neon.config.Config#readPreferences(java.lang.String, android.content.SharedPreferences)
	 */
	public void readPreference(String configKey, SharedPreferences sharedPreference) {

		if (configKey == null)
			return;

		boolean readAll = configKey.equals("");

		for (Field item : annotatedFields.values()) {
			PreferenceConfig annotation = item.getAnnotation(PreferenceConfig.class);
			Object value = null;
			Object defaultValue = null;
			String key = "";
			try {
				key = annotation.key();
				if (DEFAULT_KEY.equals(annotation.key())) {
					key = item.getName();
				}

				// se configKey non specificato o se è proprio quello
				if (key.equals(configKey) || readAll) {

					defaultValue = item.get(this);
					value = retrievePreference(item, sharedPreference, key, annotation);

					// se abbiamo un valore null, utilizziamo il valore di
					// default
					if (value == null)
						value = defaultValue;

					item.set(this, value);
					
					if (!configKey.equals("")) {
						// usciamo dato che abbiamo finito
						return;
					}
				}

			} catch (Exception e) {
				Logger.error("Error on load config \"%s\" from preference \"%s\" - BinderDefault BinderValue %s", item.getName(), key, defaultValue);
				e.printStackTrace();
			}

		}
	}

	@Override
	public void writePreference(Context context, String configKey) {
		//SharedPreferences preference=context.getSharedPreferences(ApplicationManager.DEFAULT_SHARED_PREFERENCES_NAME, 0);
		
		//writePreference(configKey, preference);
		
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.abubu.neon.config.Config#writePreference(android.content. SharedPreferences)
	 */
	public void writePreferences(SharedPreferences sharedPreference) {
		writePreference("", sharedPreference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.abubu.neon.config.Config#writePreference(java.lang.String, android.content.SharedPreferences)
	 */
	public void writePreference(String configKey, SharedPreferences sharedPreference) {
		if (configKey == null)
			return;

		Editor edit = sharedPreference.edit();

		try {
			for (Field item : annotatedFields.values()) {
				PreferenceConfig annotation = item.getAnnotation(PreferenceConfig.class);
				String key;
				try {
					key = annotation.key();
					if (DEFAULT_KEY.equals(annotation.key())) {
						key = item.getName();
					}

					if (key.equals(configKey) || configKey.equals("")) {
						persistPreference(edit, item, sharedPreference, key, annotation);

						if (!configKey.equals("")) {
							// usciamo dato che abbiamo finito
							return;
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// effettuiamo il commit delle modifiche
			edit.commit();
			// ElioLogger.debug("Commito tutte le modifiche");
		}

	}

	/**
	 * Legge i parametri presenti in un item e copia i relativi valori nel config
	 * 
	 * @param itemSource
	 */
	/*
	public void readPackItem(PackItem itemSource) {

		for (Field item : annotatedFields.values()) {
			PreferenceConfig annotation = item.getAnnotation(PreferenceConfig.class);
			Object value = null;
			Object defaultValue = null;
			String key = "";
			try {
				key = annotation.key();
				if (DEFAULT_KEY.equals(annotation.key())) {
					key = item.getName();
				}

				if (itemSource.contains(key)) {
					// assert: esiste una resource con lo stesso nome di
					// questo field
					defaultValue = item.get(this);
					value = retrieveResourceValue(item, itemSource.getResource(key), key, annotation);

					// se abbiamo un valore null, utilizziamo il valore di
					// default
					if (value == null)
						value = defaultValue;

					item.set(this, value);

					ElioLogger.debug("Load config \"%s\" from resource \"%s\" - BinderValue \"%s\" - BinderDefault BinderValue %s", item.getName(), itemSource.getPath(), value, defaultValue);
				}

			} catch (Exception e) {
				ElioLogger.error("Error on load config \"%s\" from preference \"%s\" - BinderDefault BinderValue %s", item.getName(), key, defaultValue);

				e.printStackTrace();
			}

		}
	}*/

	/**
	 * Dato un field ed una shared preference, prende il valore dell'oggetto e la salva
	 * 
	 * @param currentItem
	 * @param sharedPreference
	 * @param key
	 * @param converter
	 * @param object
	 * @return
	 */
	protected void persistPreference(Editor edit, Field field, SharedPreferences sharedPreferences, String key, PreferenceConfig annotation) {
		Object temp;
		try {
			PreferenceType typePreference = annotation.preferenceType();
			Converter conv = annotation.converter().newInstance();
			temp = conv.convertToPreference(field.get(this), typePreference);

			Logger.debug("Save config into preference \"%s\" - BinderValue \"%s\" type %s", key, temp, typePreference);

			switch (typePreference) {
			case STRING: {
				if (annotation.crypted()) {
					temp = ConfigHelper.encodedString((String) temp);
				}

				edit.putString(key, (String) temp);
			}
				break;
			case BOOL:
				if (temp != null && (temp.getClass() == Boolean.TYPE || temp.getClass() == Boolean.class)) {
					edit.putBoolean(key, (Boolean) temp);
				} else {
					Logger.warn("NON STO SALVANDO %s", key);
				}
				break;
			case INT:
				if (temp != null && (temp.getClass() == Integer.TYPE || temp.getClass() == Integer.class)) {
					edit.putInt(key, (Integer) temp);
				} else {
					Logger.warn("NON STO SALVANDO %s", key);
				}
				break;
			default:
				Logger.warn("NON STO SALVANDO %s", key);
			}
			;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Dato un field, ed una shared preference, recupera la key opportuna ed eventualmente la converte
	 * 
	 * @param field
	 * @param sharedPreferences
	 * @param key
	 * @param converter
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	protected Object retrievePreference(Field field, SharedPreferences sharedPreferences, String key, PreferenceConfig annotation) throws Exception {
		Object ret = null;

		PreferenceType typePreference = annotation.preferenceType();
		Converter conv = annotation.converter().newInstance();

		// MyLogger.debug(String.format("Read config=\"%s\" from pref=\"%s\" (Type %s) ",field.getName(),
		// key, typePreference));

		switch (typePreference) {
		case STRING: {
			Object fieldValue = field.get(this);

			String value = sharedPreferences.getString(key, (String) conv.convertToPreference(fieldValue, typePreference));
			
			if (annotation.crypted()) {
				value = ConfigHelper.decodeString((String) value);
			}

			//ret = conv.convertToConfig(field, value);
		}
			break;
		case BOOL: {
			Boolean value = sharedPreferences.getBoolean(key, (Boolean) conv.convertToPreference(field.get(this), typePreference));
			//ret = conv.convertToConfig(field, value);
		}
			break;
		case INT: {
			Integer value = sharedPreferences.getInt(key, (Integer) conv.convertToPreference(field.get(this), typePreference));
			//ret = conv.convertToConfig(field, value);
		}
			break;
		}
		;

		return ret;
	}

	/**
	 * Dato un field, ed una shared preference, recupera la key opportuna ed eventualmente la converte
	 * 
	 * @param field
	 * @param sharedPreferences
	 * @param key
	 * @param converter
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 *//*
	protected Object retrieveResourceValue(Field field, PackResource resourceSource, String key, PreferenceConfig annotation) throws Exception {
		Object ret = null;

		PreferenceType typePreference = annotation.preferenceType();
		Converter conv = annotation.converter().newInstance();

		// MyLogger.debug(String.format("Read config=\"%s\" from pref=\"%s\" (Type %s) ",field.getName(),
		// key, typePreference));

		switch (typePreference) {
		case STRING: {
			// Object fieldValue = field.get(this);

			String value;
			// se possiamo mettere la stringa risolta bene
			if (resourceSource instanceof StringPackResource) {
				value = ((StringPackResource) resourceSource).value;
			} else if (resourceSource instanceof RefArrayPackResource) {
				value = ((RefArrayPackResource) resourceSource).toString();
			} else if (resourceSource instanceof RefPackResource) {
				value = ((RefPackResource) resourceSource).refPath;
			} else {
				// altririmenti mettiamo il tag della risorsa
				value = resourceSource.getPath();
			}
			ret = conv.convertToConfig(field, value);
		}
			break;
		case INT: {
			String value;
			// se possiamo mettere la stringa risolta bene
			if (resourceSource instanceof StringPackResource) {
				value = ((StringPackResource) resourceSource).value;
			} else {
				// altririmenti mettiamo il tag della risorsa
				value = resourceSource.getPath();
			}
			ret = conv.convertToConfig(field, value);
		}
			break;
		default:
			break;
		}
		;

		return ret;
	}*/
}
