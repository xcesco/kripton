package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.BindTypeAdapter;

public class Enum87IntegerTypeAdapter implements BindTypeAdapter<Enum87A, Integer> {

	@Override
	public Enum87A toJava(Integer dataValue) throws Exception {
		return Enum87A.values()[dataValue];
	}

	@Override
	public Integer toData(Enum87A javaValue) throws Exception {
		return javaValue.ordinal();
	}

}
