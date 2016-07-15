package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.android.sharedprefs.ConfigBase;
import com.abubusoft.kripton.android.sharedprefs.Converter;
import com.abubusoft.kripton.android.sharedprefs.DefaultConverter;
import com.abubusoft.kripton.android.sharedprefs.PreferenceType;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface BindPreference {
	
	/**
	 * if true, means field must bind persist model
	 * @return
	 */
	boolean enabled() default true;

	/**
	 * Preference name. Default name is attribute name.
	 * 
	 * @return
	 */
	String name() default ConfigBase.DEFAULT_KEY;

	/**
	 * converter da utilizzare per la conversione tra preference e config e viceversa.
	 * 
	 * @return
	 */
	Class<? extends Converter> converter() default DefaultConverter.class;

	/**
	 * Tipo di preferenza. Normalmente è string, ma può essere anche altro.
	 * 
	 * @return
	 */
	PreferenceType preferenceType() default PreferenceType.STRING;

	/**
	 * Se true il campo viene ricopiato durante il reset, altrimenti viene ignorato.
	 * 
	 * @return
	 */
	boolean copyOnReset() default true;

	/**
	 * <p>
	 * Se true indica che il campo deve essere criptato. <b>Gli attributi di config possono essere di qualunque tipo convertibile in stringa. Funziona solo con le preference di
	 * tipo Stringa</b>
	 * </p>
	 * 
	 * @return
	 */
	boolean crypted() default false;
}
