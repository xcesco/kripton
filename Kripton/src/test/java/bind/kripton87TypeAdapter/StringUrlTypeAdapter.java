package bind.kripton87TypeAdapter;

import java.net.URL;

import com.abubusoft.kripton.BindTypeAdapter;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class StringUrlTypeAdapter implements BindTypeAdapter<String, URL> {

	@Override
	public String toJava(URL dataValue) throws Exception {
		return dataValue.toExternalForm();
	}

	@Override
	public URL toData(String javaValue) throws Exception {
		return new URL(javaValue);
	}



}
