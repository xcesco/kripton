package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.SqlTypeAdapter;

public class TypeAdapterByte implements SqlTypeAdapter<String, Byte> {

	@Override
	public String toJava(Byte dataValue) {
		if (dataValue!=null) {
			return ""+dataValue;
		}
		return null;
	}

	@Override
	public Byte toData(String javaValue) {
		if (javaValue!=null) {
			return Byte.valueOf(javaValue);
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return ""+Byte.valueOf(javaValue);
		}
		return null;
	}

}
