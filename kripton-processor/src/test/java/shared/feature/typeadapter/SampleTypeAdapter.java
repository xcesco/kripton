package shared.feature.typeadapter;

import com.abubusoft.kripton.android.sharedprefs.PreferenceTypeAdapter;

public class SampleTypeAdapter implements PreferenceTypeAdapter<Integer, String> {

	@Override
	public Integer toJava(String dataValue) {
		return 1;
	}

	@Override
	public String toData(Integer javaValue) {
		return "1";
	}

}
