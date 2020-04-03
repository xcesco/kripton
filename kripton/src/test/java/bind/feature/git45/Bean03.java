package bind.feature.git45;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean03 {
	List<Integer> miscellaneus;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getMiscellaneus() {
		return miscellaneus;
	}

	public void setMiscellaneus(List<Integer> miscellaneus) {
		this.miscellaneus = miscellaneus;
	}
}
