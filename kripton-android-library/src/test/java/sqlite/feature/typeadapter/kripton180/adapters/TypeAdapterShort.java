package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.SqlTypeAdapter;

public class TypeAdapterShort implements SqlTypeAdapter<String, Short> {

	@Override
	public String toJava(Short dataValue) {
		if (dataValue!=null) {
			return String.valueOf(dataValue);
		}
		return null;
	}

	@Override
	public Short toData(String javaValue) {
		if (javaValue!=null) {
			return Short.valueOf(javaValue);
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return ""+Short.valueOf(javaValue);
		}
		return null;
	}

}
