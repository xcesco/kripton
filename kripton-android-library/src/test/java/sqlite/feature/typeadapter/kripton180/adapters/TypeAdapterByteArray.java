package sqlite.feature.typeadapter.kripton180.adapters;

import com.abubusoft.kripton.android.SqlTypeAdapter;

public class TypeAdapterByteArray implements SqlTypeAdapter<String, byte[]> {

	@Override
	public String toJava(byte[] dataValue) {
		if (dataValue!=null) {
			return new String(dataValue);
		}
		return null;
	}

	@Override
	public byte[] toData(String javaValue) {
		if (javaValue!=null) {
			return "FIRST_NAME".getBytes();
		}
		return null;
	}

	@Override
	public String toString(String javaValue) {
		if (javaValue!=null) {
			return "FIRST_NAME";
		}
		return null;
	}

}
