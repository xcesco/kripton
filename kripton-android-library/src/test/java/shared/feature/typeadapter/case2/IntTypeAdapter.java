package shared.feature.typeadapter.case2;

import com.abubusoft.kripton.android.sharedprefs.PreferenceTypeAdapter;

public class IntTypeAdapter implements PreferenceTypeAdapter<Integer, String> {

	@Override
	public Integer toJava(String dataValue) {
		return 1;
	}

	@Override
	public String toData(Integer javaValue) {
		return "1";
	}

}
