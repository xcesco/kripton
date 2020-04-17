package com.abubusoft.kripton.processor;

import java.util.HashSet;
import java.util.Set;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class KriptonDynamicClassManager {

	private static final String KRIPTON_X_DATABASE_CLASS_NAME = "androidx.sqlite.db.SupportSQLiteDatabase";

	private static final String KRIPTON_X_STATEMENT_CLASS_NAME = "androidx.sqlite.db.SupportSQLiteStatement";
	
	private static final String KRIPTON_X_PREFERENCE_MANAGER_CLASS_NAME = "androidx.preference.PreferenceManager";
	/*
	 * private static final String
	 * KRIPTON_X_PAGED_LIVE_DATA_HANDLER_IMPL_CLASS_NAME =
	 * "com.abubusoft.kripton.androidx.livedata.KriptonXPagedLiveDataHandlerImpl";
	 * private static final String
	 * KRIPTON_X_PAGED_LIVE_DATA_HANDLER_IMPL_CLASS_NAME =
	 * "com.abubusoft.kripton.androidx.livedata.KriptonXPagedLiveDataHandlerImpl";
	 */

	private static final String KRIPTON_X_PAGED_LIVE_DATA_HANDLER_IMPL_CLASS_NAME = "com.abubusoft.kripton.androidx.livedata.KriptonXPagedLiveDataHandlerImpl";

	private static final String KRIPTON_X_PAGED_LIVE_DATA_CLASS_NAME = "com.abubusoft.kripton.androidx.livedata.PagedLiveData";

	private static final String KRIPTON_X_MUTABLE_LIVE_DATA_CLASS_NAME = "androidx.lifecycle.MutableLiveData";

	private static final String KRIPTON_X_LIVE_DATA_CLASS_NAME = "com.abubusoft.kripton.androidx.livedata.KriptonXLiveData";

	private static final String KRIPTON_X_LIVE_DATA_HANDLER_IMPL_CLASS_NAME = "com.abubusoft.kripton.androidx.livedata.KriptonXLiveDataHandlerImpl";

	private static KriptonDynamicClassManager instance;

	public static KriptonDynamicClassManager getInstance() {
		return instance;
	}

	private Set<String> liveDataClazzSet = new HashSet<>();

	private ClassName preferenceManagerClazz;

	public static void init(String androidxSupportValue, String androidxDbSupportValue) {
		boolean bAndroidXSupportVale = "true".equals(androidxSupportValue) ? true : false;
		boolean bAndroidXDBSupportVale = "true".equals(androidxDbSupportValue) ? true : false;

		if (instance == null) {
			instance = new KriptonDynamicClassManager(bAndroidXSupportVale, bAndroidXDBSupportVale);

			instance.liveDataClazzSet.clear();

			// define database support layer classes
			instance.databaseClazz = ClassName.bestGuess(KRIPTON_X_DATABASE_CLASS_NAME);
			instance.statementClazz = ClassName.bestGuess(KRIPTON_X_STATEMENT_CLASS_NAME);
			/*
			 * if (instance.androidXDB) {
			 * 
			 * } else { instance.databaseClazz=ClassName.bestGuess(""); }
			 */

			if (instance.androidxSupport) {
				instance.liveDataHandlerClazz = ClassName.bestGuess(KRIPTON_X_LIVE_DATA_HANDLER_IMPL_CLASS_NAME);
				instance.liveDataClazz = ClassName.bestGuess(KRIPTON_X_LIVE_DATA_CLASS_NAME);
				instance.mutableLiveDataClazz = ClassName.bestGuess(KRIPTON_X_MUTABLE_LIVE_DATA_CLASS_NAME);

				instance.pagedLiveDataClazz = ClassName.bestGuess(KRIPTON_X_PAGED_LIVE_DATA_CLASS_NAME);
				instance.pagedLiveDataHandlerClazz = ClassName
						.bestGuess(KRIPTON_X_PAGED_LIVE_DATA_HANDLER_IMPL_CLASS_NAME);

				instance.liveDataClazzSet.add("androidx.lifecycle.LiveData");
				
				instance.preferenceManagerClazz=ClassName.bestGuess(KRIPTON_X_PREFERENCE_MANAGER_CLASS_NAME);
			} else {
				instance.liveDataHandlerClazz = ClassName
						.bestGuess("com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl");
				instance.liveDataClazz = ClassName.bestGuess("com.abubusoft.kripton.android.livedata.KriptonLiveData");
				instance.mutableLiveDataClazz = ClassName.bestGuess("android.arch.lifecycle.MutableLiveData");

				instance.pagedLiveDataClazz = ClassName
						.bestGuess("com.abubusoft.kripton.android.livedata.PagedLiveData");
				instance.pagedLiveDataHandlerClazz = ClassName
						.bestGuess("com.abubusoft.kripton.android.livedata.KriptonPagedLiveDataHandlerImpl");

				instance.liveDataClazzSet.add("android.arch.lifecycle.LiveData");
				
				instance.preferenceManagerClazz=ClassName.bestGuess("android.preference.PreferenceManager");
			}

			instance.liveDataClazzSet.add(instance.liveDataClazz.toString());
			instance.liveDataClazzSet.add(instance.mutableLiveDataClazz.toString());
			instance.liveDataClazzSet.add(instance.pagedLiveDataClazz.toString());
		}
	}

	public ClassName getPreferenceManagerClazz() {
		return preferenceManagerClazz;
	}

	/**
	 * test purpouse
	 */
	public static void reset() {
		instance = null;
	}

	private boolean androidxSupport;

	public boolean isAndroidxSupport() {
		return androidxSupport;
	}

	private boolean androidXDB;

	public boolean isAndroidXDB() {
		return androidXDB;
	}

	private ClassName liveDataClazz;
	private ClassName liveDataHandlerClazz;
	private ClassName mutableLiveDataClazz;
	private ClassName pagedLiveDataClazz;
	private ClassName pagedLiveDataHandlerClazz;

	private ClassName databaseClazz;
	private ClassName statementClazz;

	public ClassName getStatementClazz() {
		return statementClazz;
	}

	public ClassName getDatabaseClazz() {
		return databaseClazz;
	}

	private KriptonDynamicClassManager(boolean value, boolean bAndroidXDBSupportVale) {
		androidxSupport = value;
		androidXDB = bAndroidXDBSupportVale;
	}

	public ClassName getLiveDataClazz() {
		return liveDataClazz;
	}

	public ClassName getLiveDataHandlerClazz() {
		return liveDataHandlerClazz;
	}

	public ClassName getMutableLiveDataClazz() {
		return this.mutableLiveDataClazz;
	}

	public ClassName getPagedLiveDataClazz() {
		return pagedLiveDataClazz;
	}

	public ClassName getPagedLiveDataHandlerClazz() {
		return pagedLiveDataHandlerClazz;
	}

	public boolean isLiveData(String wrapperName) {
		return this.liveDataClazzSet.contains(wrapperName);

	}

	public boolean isPagedLiveData(TypeName liveDataReturnClass) {
		if (liveDataReturnClass instanceof ParameterizedTypeName) {
			ParameterizedTypeName p = (ParameterizedTypeName) liveDataReturnClass;
			ClassName r = p.rawType;
			return pagedLiveDataClazz.toString().equals(r.toString());
		}

		return false;
	}
}
