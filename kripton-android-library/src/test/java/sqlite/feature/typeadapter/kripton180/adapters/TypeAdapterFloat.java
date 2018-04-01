package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;

public class TypeAdapterFloat implements BindSQLTypeAdapter<String, Float> {

	@Override
	public String toJava(Float dataValue) {
		if (dataValue!=null) {
			return String.valueOf(dataValue);
		}
		return null;
	}

	@Override
	public Float toData(String javaValue) {
		if (javaValue!=null) {
			return Float.parseFloat(javaValue);
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return ""+Float.parseFloat(javaValue);
		}
		return null;
	}

}
