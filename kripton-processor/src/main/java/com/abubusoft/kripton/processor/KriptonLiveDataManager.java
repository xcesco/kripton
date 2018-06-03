package com.abubusoft.kripton.processor;

public class KriptonLiveDataManager {

	private boolean androidxSupport;
	private Class<?> liveDataClazz;
	private Class<?> mutableLiveDataClazz;

	public Class<?> getLiveDataClazz() {
		return liveDataClazz;
	}

	private Class<?> computableLiveDataClazz;

	public Class<?> getComputableLiveDataClazz() {
		return computableLiveDataClazz;
	}

	private KriptonLiveDataManager(Boolean value) {
		androidxSupport = value;
	}
	
	public static void init(String paramValue) {
		Boolean value = "true".equals(paramValue) ? true : false;

		if (instance == null) {
			instance = new KriptonLiveDataManager(value);

			if (instance.androidxSupport) {
				instance.computableLiveDataClazz = com.abubusoft.kripton.androidx.livedata.KriptonXComputableLiveData.class;
				instance.liveDataClazz = com.abubusoft.kripton.androidx.livedata.KriptonXLiveData.class;
				instance.mutableLiveDataClazz = androidx.lifecycle.MutableLiveData.class;
			} else {
				instance.computableLiveDataClazz = com.abubusoft.kripton.android.livedata.KriptonComputableLiveData.class;
				instance.liveDataClazz = com.abubusoft.kripton.android.livedata.KriptonLiveData.class;
				instance.mutableLiveDataClazz = android.arch.lifecycle.MutableLiveData.class;
			}
		}
	}

	public boolean isLiveData(String wrapperName) {
		if (!androidxSupport
				&& (com.abubusoft.kripton.android.livedata.KriptonLiveData.class.getName().equals(wrapperName)
						|| android.arch.lifecycle.MutableLiveData.class.getName().equals(wrapperName)
						|| android.arch.lifecycle.LiveData.class.getName().equals(wrapperName))) {
			return true;
		}

		if (androidxSupport
				&& (com.abubusoft.kripton.androidx.livedata.KriptonXLiveData.class.getName().equals(wrapperName)
						|| androidx.lifecycle.MutableLiveData.class.getName().equals(wrapperName)
						|| androidx.lifecycle.LiveData.class.getName().equals(wrapperName))) {
			return true;
		}

		return false;
	}

	private static KriptonLiveDataManager instance;

	public static KriptonLiveDataManager getInstance() {
		return instance;
	}

	public Class<?> getMutableLiveDataClazz() {
		return this.mutableLiveDataClazz;
	}

	/**
	 * test purpouse
	 */
	public static void reset() {
		instance=null;
	}
}
