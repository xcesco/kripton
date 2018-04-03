package shared.feature.typeadapter.case1;

import com.abubusoft.kripton.android.sharedprefs.PreferenceTypeAdapter;

public class SampleTypeAdapter implements PreferenceTypeAdapter<String, String> {

	@Override
	public String toJava(String dataValue) {
		return "1";
	}

	@Override
	public String toData(String javaValue) {
		return "1";
	}

}
