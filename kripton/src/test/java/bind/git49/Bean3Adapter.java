package bind.git49;

import com.abubusoft.kripton.TypeAdapter;

public class Bean3Adapter implements TypeAdapter<Bean3, String> {

	@Override
	public Bean3 toJava(String dataValue) {
		return new Bean3();
	}

	@Override
	public String toData(Bean3 javaValue) {
		return javaValue.name;
	}

}
