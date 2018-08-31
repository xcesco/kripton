package com.abubusoft.kripton.androidx.livedata;

import androidx.lifecycle.MutableLiveData;

public class KriptonXLiveData<T> extends MutableLiveData<T> {

	public void updateValue(T value) {
		this.postValue(value);
	}
}