/**
 * 
 */
package com.abubusoft.kripton.android.sqlite.livedata;

import android.arch.lifecycle.LiveData;

// TODO: Auto-generated Javadoc
/**
 * The Class KriptonLiveData.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <T> the generic type
 */
public class KriptonLiveData<T> extends LiveData<T> {

	/**
	 * Update value.
	 *
	 * @param value the value
	 */
	public void updateValue(T value) {
		this.postValue(value);
	}
}
