/**
 * 
 */
package com.abubusoft.kripton.android.sharedprefs;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

/**
 * Convertitore di default.
 * 
 * @author Francesco Benincasa
 * 
 */
public class DefaultConverter implements Converter {

	@Override
	public Object convertToPreference(Object configValue, PreferenceType preferenceType) {
		Object preferenceValue = null;
		if (configValue == null)
			return null;

		switch (preferenceType) {
		case STRING:
			if (configValue != null) {
				if (configValue.getClass().isArray()) {
					if (configValue.getClass().getComponentType() == String.class) {
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

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	@Override
	public Object convertToConfig(Object preferenceValue, Class<?> propertyType) {
		Object convertedValue = null;

		// se è null non facciamo nulla
		if (preferenceValue == null)
			return null;

		if (preferenceValue.getClass() == String.class) {
			// siamo in presenza di una stringa come preference
			String preferenceString = (String) preferenceValue;

			// per default e nel caso di stringhe
			convertedValue = preferenceString;

			if (propertyType == Integer.TYPE || propertyType == Integer.class) {
				if (preferenceString.startsWith("#")) {
					// assert: è un colore, dobbiamo convertirlo
					convertedValue = Color.parseColor(preferenceString);
				} else {
					convertedValue = Integer.parseInt(preferenceString);
				}
			} else if (propertyType == Float.TYPE || propertyType == Float.class) {
				convertedValue = Float.parseFloat(preferenceString);
			} else if (propertyType == Boolean.TYPE || propertyType == Boolean.class) {
				convertedValue = Boolean.parseBoolean(preferenceString);
			} else if (propertyType.isEnum()) {
				// il field type è di tipo enum
				convertedValue = Enum.valueOf((Class<Enum>) propertyType, (String) preferenceValue);
			} else if (propertyType.isArray()) {
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
				
				if (propertyType.getComponentType() == String.class) {
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
			if (propertyType == Integer.TYPE || propertyType == Integer.class) {
				convertedValue = preferenceValue;
			}
		} else if (preferenceValue.getClass() == Boolean.TYPE || preferenceValue.getClass() == Boolean.class) {
			if (propertyType == Boolean.TYPE || propertyType == Boolean.class) {
				convertedValue = preferenceValue;
			}
		} else if (preferenceValue.getClass() == Float.TYPE || preferenceValue.getClass() == Float.class) {
			if (propertyType == Float.TYPE || propertyType == Float.class) {
				convertedValue = preferenceValue;
			}
		}

		return convertedValue;
	}
}
