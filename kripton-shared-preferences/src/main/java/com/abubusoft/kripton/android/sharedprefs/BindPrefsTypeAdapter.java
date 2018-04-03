/**
 * 
 */
package com.abubusoft.kripton.android.sharedprefs;

import com.abubusoft.kripton.BindTypeAdapter;

/**
 * Allows to manage a field of type J as a field of type D. It's usefully for unsupported type.   
 *  
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 * @param <J>
 * 		field type
 * @param <D>
 * 		data format
 */
public interface BindPrefsTypeAdapter<J, D> extends BindTypeAdapter<J, D> {

}
