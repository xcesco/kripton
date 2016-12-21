package bind.kripton87TypeAdapter;

import java.net.URL;

import com.abubusoft.kripton.BindTypeAdapter;

public class UrlByteArrayTypeAdapter implements BindTypeAdapter<URL, byte[]> {

	@Override
	public URL toJava(byte[] dataValue) throws Exception {
		return new URL(new String(dataValue));
	}

	@Override
	public byte[] toData(URL javaValue) throws Exception {
		return javaValue.toExternalForm().getBytes();
	}

}
