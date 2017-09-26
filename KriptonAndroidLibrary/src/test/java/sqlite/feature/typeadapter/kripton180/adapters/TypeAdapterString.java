package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;

public class TypeAdapterString implements BindSQLTypeAdapter<String, String> {

	@Override
	public String toJava(String dataValue) {
		if (dataValue!=null) {
			return new String(dataValue);
		}
		return null;
	}

	@Override
	public String toData(String javaValue) {
		if (javaValue!=null) {
			return javaValue;
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
