package bind.feature.git43;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean03 {
	List<Integer> miscellaneus;

	public List<Integer> getMiscellaneus() {
		return miscellaneus;
	}

	public void setMiscellaneus(List<Integer> miscellaneus) {
		this.miscellaneus = miscellaneus;
	}
}
