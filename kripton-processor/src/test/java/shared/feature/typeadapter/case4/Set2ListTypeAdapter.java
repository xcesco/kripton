package shared.feature.typeadapter.case4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.sharedprefs.PreferenceTypeAdapter;

public class Set2ListTypeAdapter implements PreferenceTypeAdapter<List<String>, Set<String>> {

	@Override
	public List<String> toJava(Set<String> dataValue) {
		ArrayList<String> result=new ArrayList<String>();
		result.add("1");
		result.add("2");
		return result;
	}

	@Override
	public Set<String> toData(List<String> javaValue) {
		HashSet<String> result=new HashSet<>();
		
		result.add("1");
		result.add("2");
		
		return result;
	}



}
