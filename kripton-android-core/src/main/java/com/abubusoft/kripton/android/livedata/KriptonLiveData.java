/**
 * 
 */
package com.abubusoft.kripton.android.livedata;

import android.arch.lifecycle.MutableLiveData;

/**
 * The Class KriptonLiveData.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <T>
 *            the generic type
 */
public class KriptonLiveData<T> extends MutableLiveData<T> {

	/**
	 * Update value.
	 *
	 * @param value
	 *            the value
	 */
	public void updateValue(T value) {
		this.postValue(value);
	}
}
