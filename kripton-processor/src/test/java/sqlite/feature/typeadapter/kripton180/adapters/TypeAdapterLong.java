package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.SqlTypeAdapter;

public class TypeAdapterLong implements SqlTypeAdapter<String, Long> {

	@Override
	public String toJava(Long dataValue) {
		if (dataValue!=null) {
			return String.valueOf(dataValue);
		}
		return null;
	}

	@Override
	public Long toData(String javaValue) {
		if (javaValue!=null) {
			return Long.valueOf(javaValue);
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return ""+Long.valueOf(javaValue);
		}
		return null;
	}

}
