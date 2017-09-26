package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;

public class TypeAdapterLastName implements BindSQLTypeAdapter<String, Double> {

	@Override
	public String toJava(Double dataValue) {
		if (dataValue==12.0) {
			return "LAST_NAME";
		}
		return null;
	}

	@Override
	public Double toData(String javaValue) {
		if (javaValue!=null) {
			return 12.0;
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return "12";
		}
		return null;
	}

}
