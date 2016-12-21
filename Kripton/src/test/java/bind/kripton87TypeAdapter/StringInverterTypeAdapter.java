package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.BindTypeAdapter;

public class StringInverterTypeAdapter implements BindTypeAdapter<String, String> {

	@Override
	public String toJava(String dataValue) {
		return new StringBuilder(dataValue).reverse().toString();
	}

	@Override
	public String toData(String javaValue) {
		return new StringBuilder(javaValue).reverse().toString();
	}

}
