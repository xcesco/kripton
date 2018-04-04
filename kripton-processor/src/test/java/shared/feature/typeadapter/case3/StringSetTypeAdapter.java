package shared.feature.typeadapter.case3;

import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.android.sharedprefs.PreferenceTypeAdapter;

public class StringSetTypeAdapter implements PreferenceTypeAdapter<String, Set<String>> {

	@Override
	public String toJava(Set<String> dataValue) {
		return "1|2";
	}

	@Override
	public Set<String> toData(String javaValue) {
		HashSet<String> result=new HashSet<>();
		
		result.add("1");
		result.add("2");
		
		return result;
	}



}
