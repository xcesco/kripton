package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.SqlTypeAdapter;

public class TypeAdapterChar implements SqlTypeAdapter<String, Character> {

	@Override
	public String toJava(Character dataValue) {
		if (dataValue!=null) {
			return String.valueOf(dataValue);
		}
		return null;
	}

	@Override
	public Character toData(String javaValue) {
		if (javaValue!=null) {
			return javaValue.charAt(0);
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return ""+javaValue.charAt(0);
		}
		return null;
	}

}
