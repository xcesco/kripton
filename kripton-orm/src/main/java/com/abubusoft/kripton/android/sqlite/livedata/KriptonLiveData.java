/**
 * 
 */
package com.abubusoft.kripton.android.sqlite.livedata;

import android.arch.lifecycle.LiveData;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonLiveData<T> extends LiveData<T> {

	public void updateValue(T value) {
		this.postValue(value);
	}
}
