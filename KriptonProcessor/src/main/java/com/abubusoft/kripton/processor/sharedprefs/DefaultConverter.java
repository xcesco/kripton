/**
 * 
 */
package com.abubusoft.kripton.processor.sharedprefs;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.abubu.elio.logger.ElioLogger;
import org.abubu.elio.pack.PackItem;
import org.abubu.elio.pack.PackManager;
import org.abubu.elio.pack.PackResource;
import org.abubu.elio.pack.PackUrl;
import org.abubu.elio.pack.PackEntityType;

import android.graphics.Color;

/**
 * Convertitore di default.
 * 
 * @author Francesco Benincasa
 * 
 */
public class DefaultConverter implements Converter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.abubu.neon.preference.Converter#convertToPreference(java.lang.Object,
	 * org.abubu.neon.preference.TypePreference)
	 */
	@Override
	public Object convertToPreference(Object configValue, PreferenceType preferenceType) {
		Object preferenceValue = null;
		if (configValue == null)
			return null;

		switch (preferenceType) {
		case STRING:
			if (configValue != null) {
				if (configValue.getClass() == PackItem.class) {
					preferenceValue = ((PackItem) configValue).getPath();
				} else if (PackResource.class.isAssignableFrom(configValue.getClass())) {
					preferenceValue = ((PackResource) configValue).getPath();
				} else if (configValue.getClass().isArray()) {
					if (configValue.getClass().getComponentType() == PackItem.class) {
						String separator="";
						StringBuilder buffer=new StringBuilder();						
						PackItem[] array= (PackItem[]) configValue;
						for (PackItem item: array)
						{
							buffer.append(separator+item.getPath());
							separator=ConfigBase.STRING_ARRAY_SEPARATOR;
						}					
							
						preferenceValue=buffer.toString();
					} else if (configValue.getClass().getComponentType() == PackResource.class) {
						String separator="";
						StringBuilder buffer=new StringBuilder();						
						PackResource[] array= (PackResource[]) configValue;
						for (PackResource item: array)
						{
							buffer.append(separator+item.getPath());
							separator=ConfigBase.STRING_ARRAY_SEPARATOR;
						}
						preferenceValue=buffer.toString();
					} else if (configValue.getClass().getComponentType() == String.class) {
						String separator="";
						StringBuilder buffer=new StringBuilder();						
						String[] array= (String[]) configValue;
						for (String item: array)
						{
							buffer.append(separator+item);
							separator=ConfigBase.STRING_ARRAY_SEPARATOR;
						}
						preferenceValue=buffer.toString();
					}
				} else {
					// che sia string, int o float viene convertito
					preferenceValue = String.valueOf(configValue);
				}
			}
			;

			break;
		case BOOL:
			// siamo in una fase di bool
			if (configValue.getClass() == Boolean.TYPE || configValue.getClass() == Boolean.class) {
				preferenceValue = configValue;
			}
			break;
		case INT:
			// siamo in una fase di int
			if (configValue.getClass() == Integer.TYPE || configValue.getClass() == Integer.class) {
				preferenceValue = configValue;
			}
			break;
		default:
			break;
		}

		return preferenceValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.abubu.neon.preference.Converter#convertToConfig(java.lang.reflect
	 * .Field, java.lang.Object)
	 */
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	@Override
	public Object convertToConfig(Field field, Object preferenceValue) {
		Object convertedValue = null;

		// se è null non facciamo nulla
		if (preferenceValue == null)
			return null;

		if (preferenceValue.getClass() == String.class) {
			// siamo in presenza di una stringa come preference
			String preferenceString = (String) preferenceValue;

			// per default e nel caso di stringhe
			convertedValue = preferenceString;

			if (field.getType() == Integer.TYPE || field.getType() == Integer.class) {
				if (preferenceString.startsWith("#")) {
					// assert: è un colore, dobbiamo convertirlo
					convertedValue = Color.parseColor(preferenceString);
				} else {
					convertedValue = Integer.parseInt(preferenceString);
				}
			} else if (field.getType() == Float.TYPE || field.getType() == Float.class) {
				convertedValue = Float.parseFloat(preferenceString);
			} else if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
				convertedValue = Boolean.parseBoolean(preferenceString);
			} else if (field.getType().isEnum()) {
				// il field type è di tipo enum
				convertedValue = Enum.valueOf((Class<Enum>) field.getType(), (String) preferenceValue);
			} else if (field.getType() == PackItem.class) {
				// recupera l'item
				PackUrl url = new PackUrl(preferenceString);
				PackItem packItem = PackManager.instance().findItemByPath(url.getPath(PackEntityType.ITEM));

				convertedValue = packItem;
			} else if (field.getType() == PackResource.class) {
				PackUrl url = new PackUrl(preferenceString);
				PackResource packResource = PackManager.instance().findResourceByPath(url.getPath(PackEntityType.RESOURCE));

				convertedValue = packResource;
			} else if (field.getType().isArray()) {
				// siamo nel caso in cui il field type è un array
				
				String tempValues[]=preferenceString.split(ConfigBase.STRING_ARRAY_SEPARATOR);
				List<String> values=new ArrayList<String>();

				// ripuliamo gli array dalle stringhe vuote
				for (String item: tempValues)
				{
					if (item!=null && item.trim().length()>0)
					{
						values.add(item);
					}
				}
				
				if (field.getType().getComponentType() == PackResource.class) {
					PackUrl url;
					PackResource packResource;
					PackResource[] array= new PackResource[values.size()];
					int i=0;
					for (String item: values)
					{
						url= new PackUrl(item);
						packResource = PackManager.instance().findResourceByPath(url.getPath(PackEntityType.RESOURCE));
						
						if (packResource==null)
						{
							ElioLogger.error("Field "+field.getName()+" referenced resource ["+url.getPath(PackEntityType.RESOURCE)+"] was not found!");
						}
						
						array[i++]=packResource;
					}					
						
					convertedValue=array;
				} else if (field.getType().getComponentType() == PackItem.class) {					
					PackUrl url;
					PackItem packItem;
					PackItem[] array= new PackItem[values.size()];
					int i=0;
					for (String item: values)
					{
						url= new PackUrl(item);
						packItem = PackManager.instance().findItemByPath(url.getPath(PackEntityType.ITEM));
						
						if (packItem==null)
						{
							ElioLogger.error("Field "+field.getName()+" referenced item ["+url.getPath(PackEntityType.ITEM)+"] was not found!");
						}
						
						array[i++]=packItem;
					}					
						
					convertedValue=array;
				} else if (field.getType().getComponentType() == String.class) {
					String[] array= new String[values.size()];
					int i=0;
					for (String item: values)
					{						
						array[i++]=item;
					}					
						
					convertedValue=array;
				}
			}
		} else if (preferenceValue.getClass() == Integer.TYPE || preferenceValue.getClass() == Integer.class) {
			if (field.getType() == Integer.TYPE || field.getType() == Integer.class) {
				convertedValue = preferenceValue;
			}
		} else if (preferenceValue.getClass() == Boolean.TYPE || preferenceValue.getClass() == Boolean.class) {
			if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
				convertedValue = preferenceValue;
			}
		} else if (preferenceValue.getClass() == Float.TYPE || preferenceValue.getClass() == Float.class) {
			if (field.getType() == Float.TYPE || field.getType() == Float.class) {
				convertedValue = preferenceValue;
			}
		}

		return convertedValue;
	}
}
