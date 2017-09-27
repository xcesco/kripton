package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;

public class TypeAdapterInteger implements BindSQLTypeAdapter<String, Integer> {

	@Override
	public String toJava(Integer dataValue) {
		if (dataValue!=null) {
			return String.valueOf(dataValue);
		}
		return null;
	}

	@Override
	public Integer toData(String javaValue) {
		if (javaValue!=null) {
			return Integer.parseInt(javaValue);
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return ""+Integer.parseInt(javaValue);
		}
		return null;
	}

}
