package sqlite.feature.typeadapter;

import com.abubusoft.kripton.BindTypeAdapter;

public class PasswordAdapterType implements BindTypeAdapter<String, byte[]> {

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


	

}
