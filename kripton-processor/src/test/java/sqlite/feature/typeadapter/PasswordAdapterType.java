package sqlite.feature.typeadapter;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;

public class PasswordAdapterType implements BindSQLTypeAdapter<String, byte[]> {

	@Override
	public String toJava(byte[] dataValue) {
		if (dataValue==null) return null;
		
		return String.valueOf(dataValue);
	}

	@Override
	public byte[] toData(String javaValue) {
		if (javaValue==null) return null;
		
		return javaValue.getBytes();
	}

	@Override
	public String toString(String javaValue) {
		return null;
	}


	

}
