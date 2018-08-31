package com.abubusoft.kripton.processor;

import java.util.HashSet;
import java.util.Set;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class KriptonLiveDataManager {

	private static KriptonLiveDataManager instance;

	public static KriptonLiveDataManager getInstance() {
		return instance;
	}

	private Set<String> liveDataClazzSet = new HashSet<>();

	public static void init(String paramValue) {
		Boolean value = "true".equals(paramValue) ? true : false;

		if (instance == null) {
			instance = new KriptonLiveDataManager(value);

			instance.liveDataClazzSet.clear();

			if (instance.androidxSupport) {
				instance.liveDataHandlerClazz = com.abubusoft.kripton.androidx.livedata.KriptonXLiveDataHandlerImpl.class;
				instance.liveDataClazz = com.abubusoft.kripton.androidx.livedata.KriptonXLiveData.class;
				instance.mutableLiveDataClazz = androidx.lifecycle.MutableLiveData.class;

				instance.pagedLiveDataClazz = com.abubusoft.kripton.androidx.livedata.PagedLiveData.class;
				instance.pagedLiveDataHandlerClazz = com.abubusoft.kripton.androidx.livedata.KriptonXPagedLiveDataHandlerImpl.class;

				instance.liveDataClazzSet.add(androidx.lifecycle.LiveData.class.getName());
			} else {
				instance.liveDataHandlerClazz = com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl.class;
				instance.liveDataClazz = com.abubusoft.kripton.android.livedata.KriptonLiveData.class;
				instance.mutableLiveDataClazz = android.arch.lifecycle.MutableLiveData.class;

				instance.pagedLiveDataClazz = com.abubusoft.kripton.android.livedata.PagedLiveData.class;
				instance.pagedLiveDataHandlerClazz = com.abubusoft.kripton.android.livedata.KriptonPagedLiveDataHandlerImpl.class;

				instance.liveDataClazzSet.add(android.arch.lifecycle.LiveData.class.getName());
			}

			instance.liveDataClazzSet.add(instance.liveDataClazz.getName());
			instance.liveDataClazzSet.add(instance.mutableLiveDataClazz.getName());
			instance.liveDataClazzSet.add(instance.pagedLiveDataClazz.getName());
		}
	}

	/**
	 * test purpouse
	 */
	public static void reset() {
		instance = null;
	}

	private boolean androidxSupport;

	private Class<?> liveDataClazz;

	private Class<?> liveDataHandlerClazz;
	private Class<?> mutableLiveDataClazz;

	private Class<?> pagedLiveDataClazz;

	private Class<?> pagedLiveDataHandlerClazz;

	private KriptonLiveDataManager(Boolean value) {
		androidxSupport = value;
	}

	public Class<?> getLiveDataClazz() {
		return liveDataClazz;
	}

	public Class<?> getLiveDataHandlerClazz() {
		return liveDataHandlerClazz;
	}

	public Class<?> getMutableLiveDataClazz() {
		return this.mutableLiveDataClazz;
	}

	public Class<?> getPagedLiveDataClazz() {
		return pagedLiveDataClazz;
	}

	public Class<?> getPagedLiveDataHandlerClazz() {
		return pagedLiveDataHandlerClazz;
	}

	public boolean isLiveData(String wrapperName) {
		return this.liveDataClazzSet.contains(wrapperName);
		// if (!androidxSupport &&
		// (com.abubusoft.kripton.android.livedata.KriptonLiveData.class.getName().equals(wrapperName)
		// ||
		// android.arch.lifecycle.MutableLiveData.class.getName().equals(wrapperName)
		// ||
		// android.arch.lifecycle.LiveData.class.getName().equals(wrapperName)))
		// {
		// return true;
		// }
		//
		// if (androidxSupport &&
		// (com.abubusoft.kripton.androidx.livedata.KriptonXLiveData.class.getName().equals(wrapperName)
		// ||
		// androidx.lifecycle.MutableLiveData.class.getName().equals(wrapperName)
		// || androidx.lifecycle.LiveData.class.getName().equals(wrapperName)))
		// {
		// return true;
		// }
		//
		// return false;
	}

	public boolean isPagedLiveData(TypeName liveDataReturnClass) {
		if (liveDataReturnClass instanceof ParameterizedTypeName) {
			ParameterizedTypeName p = (ParameterizedTypeName) liveDataReturnClass;
			ClassName r = p.rawType;
			return pagedLiveDataClazz.getName().equals(r.toString());
		}

		return false;
	}
}
