package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.SqlTypeAdapter;

public class TypeAdapterBoolean implements SqlTypeAdapter<String, Boolean> {

	@Override
	public String toJava(Boolean dataValue) {
		if (dataValue!=null) {
			return String.valueOf(dataValue);
		}
		return null;
	}

	@Override
	public Boolean toData(String javaValue) {
		if (javaValue!=null) {
			return Boolean.valueOf(javaValue);
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return javaValue;
		}
		return null;
	}

}
