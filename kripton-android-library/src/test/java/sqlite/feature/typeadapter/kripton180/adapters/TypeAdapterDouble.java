package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;

public class TypeAdapterDouble implements BindSQLTypeAdapter<String, Double> {

	@Override
	public String toJava(Double dataValue) {
		if (dataValue!=null) {
			return String.valueOf(dataValue);
		}
		return null;
	}

	@Override
	public Double toData(String javaValue) {
		if (javaValue!=null) {
			return Double.valueOf(javaValue);
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return ""+Double.valueOf(javaValue);
		}
		return null;
	}

}
