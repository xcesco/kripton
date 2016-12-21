package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.BindTypeAdapter;

public class StringEnum87ATypeAdapter implements BindTypeAdapter<String, Enum87A> {

	@Override
	public String toJava(Enum87A dataValue) throws Exception {
		return dataValue.toString();
	}

	@Override
	public Enum87A toData(String javaValue) throws Exception {
		return Enum87A.valueOf(javaValue);
	}

}
